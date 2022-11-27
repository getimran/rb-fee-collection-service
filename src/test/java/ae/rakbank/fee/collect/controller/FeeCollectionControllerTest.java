package ae.rakbank.fee.collect.controller;

import ae.rakbank.fee.collect.dto.FeeCollectionRequest;
import ae.rakbank.fee.collect.dto.FeeCollectionResponse;
import ae.rakbank.fee.collect.exception.ApiInvokerServiceException;
import ae.rakbank.fee.collect.exception.FeeCollectionServiceException;
import ae.rakbank.fee.collect.exception.FeeDetailNotFoundException;
import ae.rakbank.fee.collect.exception.StudentNotFoundException;
import ae.rakbank.fee.collect.model.FeeDetail;
import ae.rakbank.fee.collect.service.impl.FeeCollectionServiceImpl;
import ae.rakbank.fee.collect.utility.TestUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class FeeCollectionControllerTest {

    @InjectMocks
    FeeCollectionController feeCollectionController;

    @Mock
    private FeeCollectionServiceImpl feeCollectionService;

    @Test
    public void testCollectFee() throws ApiInvokerServiceException, StudentNotFoundException, FeeCollectionServiceException {

        Mockito.when(feeCollectionService.collectFee(Mockito.any(FeeCollectionRequest.class)))
                        .thenReturn(TestUtility.getFeeCollectionResponse(1001));
        ResponseEntity<FeeCollectionResponse> response = feeCollectionController
                .collectFee(TestUtility.getFeeCollectionRequest(1001));
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testFindAllFeeDetailByStudentId() throws FeeDetailNotFoundException, FeeCollectionServiceException {

        List<FeeDetail> feeDetailList = new ArrayList<>();
        feeDetailList.add(TestUtility.getFeeDetail(1001));
        Mockito.when(feeCollectionService.findAllByStudentId(Mockito.anyInt()))
                .thenReturn(feeDetailList);
        ResponseEntity<List<FeeDetail>> response = feeCollectionController.findAllFeeDetailByStudentId(1001);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertTrue(!response.getBody().isEmpty());
    }

    @Test
    public void testFindFeeDetailByReferenceId() throws FeeDetailNotFoundException, FeeCollectionServiceException {

        Mockito.when(feeCollectionService.findByReferenceId(Mockito.anyString()))
                .thenReturn(TestUtility.getFeeDetail(1001));
        ResponseEntity<FeeDetail> response = feeCollectionController.findFeeDetailByReferenceId("REF0000122");
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }
}
