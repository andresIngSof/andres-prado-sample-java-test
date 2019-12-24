package com.sample.test.api.service;

import com.sample.test.api.exception.APIException;
import com.sample.test.api.request.TransactionAddRequest;
import com.sample.test.api.request.TransactionByIdRequest;
import com.sample.test.api.request.TransactionsByUserIdRequest;
import com.sample.test.api.response.SumTransactionsResponse;
import com.sample.test.api.response.TransactionAddResponse;
import com.sample.test.api.response.TransactionReportResponse;

import java.util.List;

/**
 *
 * @author : Andrés Prado Cruz
 * @email : andresss32157@gmail.com
 * @class : TransactionService.java
 */
public interface TransactionService {

    /**
     * Servicio para agregar la transaction al fichero
     * @return TransactionAddResponse
     */
    TransactionAddResponse addTransaction(TransactionAddRequest request) throws APIException;

    /**
     * Servicio para buscar una transaction por el idTransaction y user_id
     * @return TransactionAddResponse
     */
    TransactionAddResponse findTransactionById(TransactionByIdRequest request) throws APIException;

    /**
     * Servicio para buscar todas las transactions de un usuario por user_id
     * @return List<TransactionAddResponse>
     */
    List<TransactionAddResponse> findTransactionsByUserId(TransactionsByUserIdRequest request) throws APIException;

    /**
     * Servicio para calcular el monto de todas las transactions de un usuario
     * @return SumTransactionsResponse
     */
    SumTransactionsResponse sumTransactionsByUserId(TransactionsByUserIdRequest request) throws APIException;

    /**
     * Servicio que genera un reporte por semanas de las transactions de un usuario
     * @return List<TransactionReportResponse>
     */
    List<TransactionReportResponse> getReportTransaction(TransactionsByUserIdRequest request) throws APIException;

    /**
     * Servicio que regresa una transaction de manera aleatoria
     * @return TransactionAddResponse
     */
    TransactionAddResponse getRandomTransaction() throws APIException;


}