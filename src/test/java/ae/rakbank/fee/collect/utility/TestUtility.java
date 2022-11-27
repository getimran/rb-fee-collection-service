package ae.rakbank.fee.collect.utility;

import ae.rakbank.fee.collect.dto.FeeCollectionRequest;
import ae.rakbank.fee.collect.dto.FeeCollectionResponse;
import ae.rakbank.fee.collect.dto.StudentResponse;
import ae.rakbank.fee.collect.model.FeeDetail;

public class TestUtility {


    public static StudentResponse getStudentResponse(Integer studentId) {

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setStudentId(studentId);
        studentResponse.setStudentName("Imran");
        studentResponse.setGrade("4");
        return studentResponse;
    }

    public static FeeCollectionRequest getFeeCollectionRequest(Integer studentId) {

        FeeCollectionRequest feeCollectionRequest = new FeeCollectionRequest();
        feeCollectionRequest.setStudentId(studentId);
        feeCollectionRequest.setFeeAmount(10.09);
        feeCollectionRequest.setCardType("VISA");
        feeCollectionRequest.setCardNumber("727285268945");
        return feeCollectionRequest;
    }

    public static FeeCollectionResponse getFeeCollectionResponse(Integer studentId) {
        return FeeCollectionResponse.builder().feeTxnReferenceId("REF0000122")
                .studentId(studentId)
                .status("SUCCESS").build();
    }

    public static FeeDetail getFeeDetail(Integer studentId) {

        FeeDetail feeDetail = new FeeDetail();
        feeDetail.setStudentId(studentId);
        feeDetail.setFeeTxnReferenceId("REF0000122");
        feeDetail.setCardType("VISA");
        feeDetail.setCardNumber("727285268945");
        feeDetail.setFeeAmount(101.89);
        return feeDetail;
    }
}
