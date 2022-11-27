package ae.rakbank.fee.collect.utility;

import ae.rakbank.fee.collect.dto.FeeCollectionRequest;
import ae.rakbank.fee.collect.dto.FeeCollectionResponse;
import ae.rakbank.fee.collect.exception.FeeCollectionServiceException;
import ae.rakbank.fee.collect.model.FeeDetail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import static ae.rakbank.fee.collect.utility.AppConstants.SUCCESS;

/**
 * @author imran
 * Utility Class for Fee Collection Service
 */
@UtilityClass
public class FeeCollectionUtility {


    public static String convertToJson(Object object) throws FeeCollectionServiceException {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new FeeCollectionServiceException(e.getMessage());
        }
    }


    public static FeeDetail convertToEntity(FeeCollectionRequest feeCollectionRequest) {
        FeeDetail feeDetail = new ModelMapper().map(feeCollectionRequest, FeeDetail.class);
        feeDetail.setTimestamp(LocalDateTime.now());
        return feeDetail;
    }

    public static FeeCollectionResponse convertToFeeCollectionResponse(FeeDetail feeDetail) {
        return FeeCollectionResponse.builder()
                .feeTxnReferenceId(feeDetail.getFeeTxnReferenceId())
                .studentId(feeDetail.getStudentId())
                .status(SUCCESS).build();
    }
}
