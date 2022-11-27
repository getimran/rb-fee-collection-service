package ae.rakbank.fee.collect.controller;

import ae.rakbank.fee.collect.dto.FeeCollectionRequest;
import ae.rakbank.fee.collect.dto.FeeCollectionResponse;
import ae.rakbank.fee.collect.exception.ApiInvokerServiceException;
import ae.rakbank.fee.collect.exception.FeeCollectionServiceException;
import ae.rakbank.fee.collect.exception.FeeDetailNotFoundException;
import ae.rakbank.fee.collect.exception.StudentNotFoundException;
import ae.rakbank.fee.collect.model.FeeDetail;
import ae.rakbank.fee.collect.service.impl.FeeCollectionServiceImpl;
import ae.rakbank.fee.collect.utility.FeeCollectionUtility;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static ae.rakbank.fee.collect.utility.LogMessages.REQUEST_RECEIVED;
import static ae.rakbank.fee.collect.utility.LogMessages.RESPONSE_SENT;

/**
 * @author imran
 * RestController for Fee Collection Microservice
 */
@Slf4j
@RestController
@RequestMapping("/rakbank/api/fee")
@Tag(name = "Fee Collection API", description = "API for collecting students fee and retrieve fee details based on fee referenceId and studentId")
public class FeeCollectionController {

    @Autowired
    private FeeCollectionServiceImpl feeCollectionService;

    @PostMapping("/collect")
    public ResponseEntity<FeeCollectionResponse> collectFee(@Valid @RequestBody FeeCollectionRequest feeCollectionRequest)
            throws FeeCollectionServiceException, ApiInvokerServiceException, StudentNotFoundException {

        log.info(REQUEST_RECEIVED, FeeCollectionUtility.convertToJson(feeCollectionRequest));
        FeeCollectionResponse feeCollectionResponse = feeCollectionService.collectFee(feeCollectionRequest);
        log.info(RESPONSE_SENT, FeeCollectionUtility.convertToJson(feeCollectionResponse));
        return new ResponseEntity<>(feeCollectionResponse, HttpStatus.CREATED);
    }


    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<FeeDetail>> findAllFeeDetailByStudentId(@PathVariable Integer studentId)
            throws FeeCollectionServiceException, FeeDetailNotFoundException {

        log.info(REQUEST_RECEIVED, studentId);
        List<FeeDetail> feeDetailList = feeCollectionService.findAllByStudentId(studentId);
        log.info(RESPONSE_SENT, HttpStatus.OK.getReasonPhrase());
        return new ResponseEntity<>(feeDetailList, HttpStatus.OK);
    }

    @GetMapping("/{referenceId}")
    public ResponseEntity<FeeDetail> findFeeDetailByReferenceId(@PathVariable String referenceId)
            throws FeeCollectionServiceException, FeeDetailNotFoundException {

        log.info(REQUEST_RECEIVED, referenceId);
        FeeDetail feeDetail = feeCollectionService.findByReferenceId(referenceId);
        log.info(RESPONSE_SENT, HttpStatus.OK.getReasonPhrase());
        return new ResponseEntity<>(feeDetail, HttpStatus.OK);
    }
}
