package ae.rakbank.fee.collect.service.impl;

import ae.rakbank.fee.collect.dto.FeeCollectionResponse;
import ae.rakbank.fee.collect.exception.ApiInvokerServiceException;
import ae.rakbank.fee.collect.exception.FeeDetailNotFoundException;
import ae.rakbank.fee.collect.exception.StudentNotFoundException;
import ae.rakbank.fee.collect.model.FeeDetail;
import ae.rakbank.fee.collect.repository.FeeCollectionRepository;
import ae.rakbank.fee.collect.utility.TestUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class FeeCollectionServiceImplTest {

    @Mock
    private ApiInvokerServiceImpl apiInvokerService;

    @Mock
    private FeeCollectionRepository feeCollectionRepository;

    @InjectMocks
    FeeCollectionServiceImpl feeCollectionService;


    @Test
    public void testCollectFee() throws ApiInvokerServiceException, StudentNotFoundException {

        Mockito.when(apiInvokerService.invokeGetStudentByIdService(Mockito.anyInt()))
                        .thenReturn(Optional.of(TestUtility.getStudentResponse(1001)));
        Mockito.when(feeCollectionRepository.save(Mockito.any(FeeDetail.class)))
                        .thenReturn(TestUtility.getFeeDetail(1001));
         FeeCollectionResponse feeCollectionResponse = feeCollectionService
                 .collectFee(TestUtility.getFeeCollectionRequest(1001));
        Assertions.assertNotNull(feeCollectionResponse);
    }


    @Test
    public void testCollectFeeForStudentNotFound() throws ApiInvokerServiceException, StudentNotFoundException {

        Mockito.when(apiInvokerService.invokeGetStudentByIdService(Mockito.anyInt()))
                .thenReturn(Optional.empty());
        StudentNotFoundException exception = Assertions.assertThrows(StudentNotFoundException.class, () -> feeCollectionService
                .collectFee(TestUtility.getFeeCollectionRequest(1001)));
        Assertions.assertNotNull(exception);
    }

    @Test
    public void testFindAllByStudentId() throws FeeDetailNotFoundException {

        List<FeeDetail> feeDetailList = new ArrayList<>();
        feeDetailList.add(TestUtility.getFeeDetail(1001));
        Mockito.when(feeCollectionRepository.findAllByStudentId(Mockito.anyInt()))
                .thenReturn(feeDetailList);
        List<FeeDetail> feeDetailListResponse = feeCollectionService.findAllByStudentId(1001);
        Assertions.assertFalse(feeDetailListResponse.isEmpty());
    }

    @Test
    public void testFindAllByStudentIdForFeeDetailNotFound() {

        List<FeeDetail> feeDetailList = new ArrayList<>();
        Mockito.when(feeCollectionRepository.findAllByStudentId(Mockito.anyInt()))
                .thenReturn(feeDetailList);
        FeeDetailNotFoundException feeDetailNotFoundException = Assertions.assertThrows(FeeDetailNotFoundException.class,
                () -> feeCollectionService.findAllByStudentId(1001));
        Assertions.assertNotNull(feeDetailNotFoundException);
    }

    @Test
    public void testFindByReferenceId() throws FeeDetailNotFoundException {

        Mockito.when(feeCollectionRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.of(TestUtility.getFeeDetail(1001)));
        FeeDetail feeDetail= feeCollectionService.findByReferenceId("REF0000122");
        Assertions.assertNotNull(feeDetail);
    }

    @Test
    public void testFindByReferenceIdForFeeDetailNotFound() {

        Mockito.when(feeCollectionRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.empty());
        FeeDetailNotFoundException feeDetailNotFoundException = Assertions.assertThrows(FeeDetailNotFoundException.class,
                () -> feeCollectionService.findByReferenceId("REF0000122"));
        Assertions.assertNotNull(feeDetailNotFoundException);
    }
}
