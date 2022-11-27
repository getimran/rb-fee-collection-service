package ae.rakbank.fee.collect.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author imran
 * Response DTO for Fee Collection Service
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeeCollectionResponse {

    private String feeTxnReferenceId;
    private Integer studentId;
    private String status;
    private String message;
}
