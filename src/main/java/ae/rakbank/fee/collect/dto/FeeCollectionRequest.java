package ae.rakbank.fee.collect.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static ae.rakbank.fee.collect.utility.AppConstants.*;

/**
 * @author imran
 * Request DTO for Fee Collection Service
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeeCollectionRequest {

    @NotNull
    private Integer studentId;

    @NotNull
    private Double feeAmount;

    @NotBlank
    @Pattern(regexp=REGEX_ONLY_NUMBERS, message=ONLY_NUMBERS_ALLOWED)
    private String cardNumber;

    @NotBlank
    @Pattern(regexp=REGEX_ONLY_LETTERS, message=ONLY_LETTERS_ALLOWED)
    private String cardType;
}
