package ae.rakbank.fee.collect.service;

import ae.rakbank.fee.collect.dto.FeeCollectionRequest;
import ae.rakbank.fee.collect.dto.FeeCollectionResponse;
import ae.rakbank.fee.collect.exception.ApiInvokerServiceException;
import ae.rakbank.fee.collect.exception.FeeDetailNotFoundException;
import ae.rakbank.fee.collect.exception.StudentNotFoundException;
import ae.rakbank.fee.collect.model.FeeDetail;

import java.util.List;

public interface FeeCollectionService {

    public FeeCollectionResponse collectFee(FeeCollectionRequest feeCollectionRequest) throws ApiInvokerServiceException, StudentNotFoundException;
    public List<FeeDetail> findAllByStudentId(Integer studentId) throws FeeDetailNotFoundException;
    public FeeDetail findByReferenceId(String referenceId) throws FeeDetailNotFoundException;
}
