package ae.rakbank.fee.collect.service.impl;

import ae.rakbank.fee.collect.dto.StudentResponse;
import ae.rakbank.fee.collect.exception.ApiInvokerServiceException;
import ae.rakbank.fee.collect.exception.StudentNotFoundException;
import ae.rakbank.fee.collect.utility.TestUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;


import java.util.Optional;

@SpringBootTest
public class ApiInvokerServiceImplTest {



    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    ApiInvokerServiceImpl apiInvokerService;

    @BeforeEach
    public void before() {
        ReflectionTestUtils.setField(apiInvokerService, "getStudentByIdUrl", "http://localhost:8080/rakbank/api/student/");
    }

    @Test
    public void testInvokeGetStudentByIdService() throws ApiInvokerServiceException, StudentNotFoundException {

        Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.any()))
                .thenReturn(ResponseEntity.ok(TestUtility.getStudentResponse(1001)));
        Optional<StudentResponse> studentResponseOptional= apiInvokerService.invokeGetStudentByIdService(1001);
        Assertions.assertTrue(studentResponseOptional.isPresent());
    }
}
