package ae.rakbank.fee.collect.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

/**
 * @author imran
 * Response DTO for Error
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private Integer statusCode;
    private String statusDesc;
    private String message;
}
