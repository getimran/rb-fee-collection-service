package ae.rakbank.fee.collect.service.impl;

import ae.rakbank.fee.collect.dto.StudentResponse;
import ae.rakbank.fee.collect.exception.ApiInvokerServiceException;
import ae.rakbank.fee.collect.exception.StudentNotFoundException;
import ae.rakbank.fee.collect.service.ApiInvokerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetrySynchronizationManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static ae.rakbank.fee.collect.utility.AppConstants.STUDENT_NOT_FOUND;
import static ae.rakbank.fee.collect.utility.LogMessages.*;

@Slf4j
@Service
public class ApiInvokerServiceImpl implements ApiInvokerService {


    @Value("${fee-collect.student.service.url}")
    private String getStudentByIdUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Optional<StudentResponse> invokeGetStudentByIdService(Integer studentId) throws ApiInvokerServiceException, StudentNotFoundException {

        try {
            log.info(INVOKING_STUDENT_GET_SERVICE, studentId);
            log.info(ATTEMPT, RetrySynchronizationManager.getContext().getRetryCount());
            ResponseEntity<StudentResponse> studentResponseEntity = restTemplate
                    .getForEntity(getStudentByIdUrl + studentId, StudentResponse.class);
            log.info(RECEIVED_RESPONSE, studentResponseEntity.getStatusCode());
            if (studentResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
                return Optional.of(studentResponseEntity.getBody());
            }
        } catch (Exception ex) {
            log.error(ERROR_OCCURRED, ex.getMessage());
            if(ex instanceof HttpClientErrorException) {
                HttpClientErrorException httpClientErrorException = (HttpClientErrorException) ex;
                if(httpClientErrorException.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                    throw new StudentNotFoundException(STUDENT_NOT_FOUND);
                }
            }
            throw new ApiInvokerServiceException(ex.getMessage());
        }
        return Optional.empty();
    }
}
