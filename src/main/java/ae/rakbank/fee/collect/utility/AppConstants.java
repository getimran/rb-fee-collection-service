package ae.rakbank.fee.collect.utility;

import lombok.experimental.UtilityClass;

/**
 * @author imran
 * Constant class for Student Service
 */
@UtilityClass
public class AppConstants {

    public static final String FEE_REFERENCE_ID_SEQ = "fee_reference_seq";
    public static final String FEE_REFERENCE_ID_SEQ_GENERATOR = "ae.rakbank.fee.collect.generator.FeeDetailReferenceSequenceIdPrefixGenerator";
    public static final String FEE_REFERENCE_ID_SEQ_PREFIX = "REF00000";
    public static final String SUCCESS = "SUCCESS";
    public static final String STUDENT_NOT_FOUND = "Student not found";
    public static final String FEE_NOT_FOUND = "Fee Detail not found";

    public static final String REGEX_ONLY_NUMBERS = "^[0-9]*$";

    public static final String ONLY_NUMBERS_ALLOWED = "only numbers are allowed";

    public static final String REGEX_ONLY_LETTERS = "^[A-Za-z ]*$";

    public static final String ONLY_LETTERS_ALLOWED = "only letters are allowed";

    public static final String CONNECT_EXCEPTION = "ConnectException";

    public static final String STUDENT_SERVICE_UNAVAILABLE = "Student Service Unavailable";

    public static final String STUDENT_SERVICE_ERROR = "Student Service Error";
}
