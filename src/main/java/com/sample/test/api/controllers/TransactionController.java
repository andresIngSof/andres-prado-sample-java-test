package com.sample.test.api.controllers;

import com.sample.test.api.dto.Rango;
import com.sample.test.api.exception.APIException;
import com.sample.test.api.request.TransactionAddRequest;
import com.sample.test.api.request.TransactionByIdRequest;
import com.sample.test.api.request.TransactionsByUserIdRequest;
import com.sample.test.api.response.ErrorResponse;
import com.sample.test.api.response.SumTransactionsResponse;
import com.sample.test.api.response.TransactionAddResponse;
import com.sample.test.api.response.TransactionReportResponse;
import com.sample.test.api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 * @author : Andrés Prado Cruz
 * @email : andresss32157@gmail.com
 * @class : TransactionController.java
 * Clase Controller para exponer los servicios rest del API
 */

@RestController
@RequestMapping(value = "${api.web.test.context}")
@CrossOrigin
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    /**
     * Expone un servicio REST para realizar el ADD TRANSACTION solicitado en: https://github.com/cesaralcancio/simple-test
     */
    @RequestMapping(value = "${api.web.test.transaction.add}",
    		method = RequestMethod.POST, 
    		consumes = MediaType.APPLICATION_JSON, 
    		produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> addTransaction(@RequestBody TransactionAddRequest request) {

        try {
            request.checkNotNull();
            TransactionAddResponse transactionAddResponse =
                    transactionService.addTransaction(request);
            return new ResponseEntity<>(transactionAddResponse, HttpStatus.OK);
        } catch (APIException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }

    /**
     * Expone un servicio REST para realizar el SHOW TRANSACTION solicitado en: https://github.com/cesaralcancio/simple-test
     */
    @RequestMapping(value = "${api.web.test.transaction.findById}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> findByIdTransaction(@RequestBody TransactionByIdRequest request) {

        try {
            request.checkNotNull();
            TransactionAddResponse transactionAddResponse =
                    transactionService.findTransactionById(request);
            return new ResponseEntity<>(transactionAddResponse, HttpStatus.OK);
        } catch (APIException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }

    /**
     * Expone un servicio REST para realizar el LIST TRANSACTIONS solicitado en: https://github.com/cesaralcancio/simple-test
     */
    @RequestMapping(value = "${api.web.test.transaction.findByUserId}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> findByUserId(@RequestBody TransactionsByUserIdRequest request) {

        try {
            request.checkNotNull();
            List<TransactionAddResponse> transactionAddResponseList =
                    transactionService.findTransactionsByUserId(request);
            return new ResponseEntity<>(transactionAddResponseList, HttpStatus.OK);
        } catch (APIException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }

    /**
     * Expone un servicio REST para realizar el SUM TRANSACTIONS solicitado en: https://github.com/cesaralcancio/simple-test
     */
    @RequestMapping(value = "${api.web.test.transaction.sumTransactionsByUserId}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> sumTransactionsByUserId(@RequestBody TransactionsByUserIdRequest request) {

        try {
            request.checkNotNull();
            SumTransactionsResponse sumTransactionsResponse =
                    transactionService.sumTransactionsByUserId(request);
            return new ResponseEntity<>(sumTransactionsResponse, HttpStatus.OK);
        } catch (APIException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }

    /**
     * Expone un servicio REST para realizar el TRANSACTIONS REPORT SERVICE solicitado en: https://github.com/cesaralcancio/simple-test
     */
    @RequestMapping(value = "${api.web.test.transaction.getReportTransaction}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> getReportTransaction(@RequestBody TransactionsByUserIdRequest request) {

        try {
            request.checkNotNull();
            List<TransactionReportResponse> reportList =
                    transactionService.getReportTransaction(request);
            return new ResponseEntity<>(reportList, HttpStatus.OK);
        } catch (APIException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }

    /**
     * Expone un servicio REST para realizar el RANDMON SINGLE TRANSACTION solicitado en: https://github.com/cesaralcancio/simple-test
     */
    @RequestMapping(value = "${api.web.test.transaction.getRandomTransaction}",
            method=RequestMethod.GET,  produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> getRandomTransaction() {

        try {
            TransactionAddResponse response =
                    transactionService.getRandomTransaction();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (APIException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
    }

}