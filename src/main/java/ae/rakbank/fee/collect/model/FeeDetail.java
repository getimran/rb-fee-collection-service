package ae.rakbank.fee.collect.model;

import ae.rakbank.fee.collect.generator.FeeDetailReferenceSequenceIdPrefixGenerator;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDateTime;

import static ae.rakbank.fee.collect.utility.AppConstants.*;

/**
 * @author imran
 * Entity class for Student
 */
@Data
@NoArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeeDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = FEE_REFERENCE_ID_SEQ)
    @GenericGenerator(
            name = FEE_REFERENCE_ID_SEQ,
            strategy = FEE_REFERENCE_ID_SEQ_GENERATOR,
            parameters = {
                    @Parameter(name =
                            FeeDetailReferenceSequenceIdPrefixGenerator.VALUE_PREFIX_PARAMETER, value =
                            FEE_REFERENCE_ID_SEQ_PREFIX)})
    private String feeTxnReferenceId;

    private Integer studentId;
    private Double feeAmount;
    private String cardNumber;
    private String cardType;
    private LocalDateTime timestamp;

}
