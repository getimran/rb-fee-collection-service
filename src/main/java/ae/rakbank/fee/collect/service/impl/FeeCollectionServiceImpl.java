package ae.rakbank.fee.collect.service.impl;

import ae.rakbank.fee.collect.dto.FeeCollectionRequest;
import ae.rakbank.fee.collect.dto.FeeCollectionResponse;
import ae.rakbank.fee.collect.dto.StudentResponse;
import ae.rakbank.fee.collect.exception.ApiInvokerServiceException;
import ae.rakbank.fee.collect.exception.FeeDetailNotFoundException;
import ae.rakbank.fee.collect.exception.StudentNotFoundException;
import ae.rakbank.fee.collect.model.FeeDetail;
import ae.rakbank.fee.collect.repository.FeeCollectionRepository;
import ae.rakbank.fee.collect.service.FeeCollectionService;
import ae.rakbank.fee.collect.utility.FeeCollectionUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static ae.rakbank.fee.collect.utility.AppConstants.FEE_NOT_FOUND;
import static ae.rakbank.fee.collect.utility.AppConstants.STUDENT_NOT_FOUND;

@Service
public class FeeCollectionServiceImpl implements FeeCollectionService {


    @Autowired
    private ApiInvokerServiceImpl apiInvokerService;

    @Autowired
    private FeeCollectionRepository feeCollectionRepository;

    @Override
    public FeeCollectionResponse collectFee(FeeCollectionRequest feeCollectionRequest)
            throws ApiInvokerServiceException, StudentNotFoundException {

        Optional<StudentResponse> studentResponse = apiInvokerService.invokeGetStudentByIdService(feeCollectionRequest.getStudentId());
        if(!studentResponse.isPresent()) {
            throw new StudentNotFoundException(STUDENT_NOT_FOUND);
        }
        FeeDetail feeDetail = feeCollectionRepository
                .save(FeeCollectionUtility.convertToEntity(feeCollectionRequest));

        return FeeCollectionUtility.convertToFeeCollectionResponse(feeDetail);
    }

    @Override
    public List<FeeDetail> findAllByStudentId(Integer studentId) throws FeeDetailNotFoundException {
        List<FeeDetail> feeDetailList = feeCollectionRepository.findAllByStudentId(studentId);
        if(feeDetailList.isEmpty()) {
            throw new FeeDetailNotFoundException(FEE_NOT_FOUND);
        }
        return feeDetailList;
    }

    @Override
    public FeeDetail findByReferenceId(String referenceId) throws FeeDetailNotFoundException {
        Optional<FeeDetail> feeDetailOptional = feeCollectionRepository.findById(referenceId);
        if(!feeDetailOptional.isPresent()) {
            throw new FeeDetailNotFoundException(FEE_NOT_FOUND);
        }
        return feeDetailOptional.get();
    }
}
