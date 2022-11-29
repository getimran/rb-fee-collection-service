package ae.rakbank.fee.collect.service;

import ae.rakbank.fee.collect.dto.StudentResponse;
import ae.rakbank.fee.collect.exception.ApiInvokerServiceException;
import ae.rakbank.fee.collect.exception.StudentNotFoundException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.util.Optional;

/**
 * @author imran
 * Interface for Invoker Service
 */
public interface ApiInvokerService {

    @Retryable(value = ApiInvokerServiceException.class, maxAttempts = 3, backoff = @Backoff(delay = 100))
    public Optional<StudentResponse> invokeGetStudentByIdService(Integer studentId) throws ApiInvokerServiceException, StudentNotFoundException;
}
