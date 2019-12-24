package com.sample.test.api.service.impl;

import com.sample.test.api.dao.TransactionDao;
import com.sample.test.api.dto.Rango;
import com.sample.test.api.exception.APIException;
import com.sample.test.api.model.Transaction;
import com.sample.test.api.request.TransactionAddRequest;
import com.sample.test.api.request.TransactionByIdRequest;
import com.sample.test.api.request.TransactionsByUserIdRequest;
import com.sample.test.api.response.SumTransactionsResponse;
import com.sample.test.api.response.TransactionAddResponse;
import com.sample.test.api.response.TransactionReportResponse;
import com.sample.test.api.service.TransactionService;
import com.sample.test.api.utils.Constants;
import com.sample.test.api.utils.Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.TextStyle;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 *
 * @author : Andrés Prado Cruz
 * @email : andresss32157@gmail.com
 * @class : TransactionServiceImpl.java
 * Implementacion de la interface TransactionService
 * @see com.sample.test.api.service.TransactionService
 */
@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

    private static Logger LOG = Logger.getLogger(TransactionServiceImpl.class);

    @Autowired
    private TransactionDao transactionDao;


    @Override
    public TransactionAddResponse addTransaction(TransactionAddRequest request) throws APIException {
        try {
            Transaction transaction = transactionDao.create(new Transaction(request));
            return new TransactionAddResponse(transaction);
        } catch (Exception e) {
            throw new APIException(e);
        }
    }

    @Override
    public TransactionAddResponse findTransactionById(TransactionByIdRequest request) throws APIException {
        try {
            Transaction transaction = transactionDao.getById(request.getUser_id(), request.getIdTransaction());
            if(Objects.isNull(transaction)){
                throw new APIException(Constants.NO_REGISTERS);
            }
            return new TransactionAddResponse(transaction);
        } catch (Exception e) {
            throw new APIException(e);
        }
    }

    @Override
    public List<TransactionAddResponse> findTransactionsByUserId(TransactionsByUserIdRequest request) throws APIException {
        List<TransactionAddResponse> transactionAddResponseList = new ArrayList<>();
        try {
            List<Transaction> transactionList = transactionDao.getAllByUserId(request.getUser_id());
            transactionList.forEach(transaction -> transactionAddResponseList.add(new TransactionAddResponse(transaction)));

            return transactionAddResponseList.stream().
                    sorted(Comparator.comparing(TransactionAddResponse::getDate)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new APIException(e);
        }
    }

    @Override
    public SumTransactionsResponse sumTransactionsByUserId(TransactionsByUserIdRequest request) throws APIException {
        List<Transaction> transactionList = transactionDao.getAllByUserId(request.getUser_id());
        if(transactionList.isEmpty()){
            throw new APIException(Constants.USUARIO_NOT_FOUND);
        }
        AtomicReference<Double> sum = new AtomicReference<>(new Double("0.0"));
        transactionList.forEach(
                transaction -> sum.updateAndGet(v -> v + transaction.getAmount().doubleValue())
        );

        return new SumTransactionsResponse(request.getUser_id(), sum.get());
    }

    @Override
    public List<TransactionReportResponse> getReportTransaction(TransactionsByUserIdRequest request) throws APIException {
        //Se obtienen todas las transactions de un usuario en orden cronólogico
        List<Transaction> transactionList = transactionDao.getAllByUserId(request.getUser_id()).stream().
                sorted(Comparator.comparing(Transaction::getDate)).collect(Collectors.toList());

        //si no se obtienen resultados se arroja una APIException
        if(transactionList.isEmpty()){
            throw new APIException(Constants.USUARIO_NOT_FOUND);
        }

        //Se obtiene la lista de Rangos semanales en base a las fechas de las transactions del usuario
        List<Rango> rangos = Util.getWeeks(transactionList.stream().map(Transaction::getDate).collect(Collectors.toList()));

        List<TransactionReportResponse> reportResponses = new ArrayList<>();
        Map<Rango, List<Transaction>> mapReport = new HashMap<>();
        //Se llena mapReport para facilitar la generación de los registros del reporte
        rangos.forEach(rango -> {
            mapReport.put(rango, transactionList.stream().filter(t ->
                    Util.betweenLocalDate(t.getDate(), rango.getInicio(), rango.getFin()))
                    .collect(Collectors.toList()));

        });
        mapReport.forEach((k, v) -> {
            if(!v.isEmpty()){
                //Se genera un TransactionReportResponse por cada registro que se agregara al reporte
                TransactionReportResponse transactionReportResponse = new TransactionReportResponse();
                transactionReportResponse.setUser_id(request.getUser_id());
                transactionReportResponse.setQuantity(v.size());

                transactionReportResponse.setWeekFinishDate(k.getFin().toString() +" "+
                        k.getFin().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()));

                transactionReportResponse.setWeekStartDate(k.getInicio().toString() +" "+
                        k.getInicio().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()));

                AtomicReference<Double> sumAmount = new AtomicReference<>(new Double("0.0"));
                v.forEach(
                        transaction -> sumAmount.updateAndGet(value -> value + transaction.getAmount().doubleValue())
                );
                transactionReportResponse.setAmount(sumAmount.get());

                reportResponses.add(transactionReportResponse);
            }
        });

        //Se calcula los total_amount de cada registro del reporte
        for(int i = 0; i< reportResponses.size() ; i++){
            if(i == 0){
                reportResponses.get(i).setTotal_amount(new Double("0.0"));
            }else{
                reportResponses.get(i).setTotal_amount(reportResponses.get(i-1).getAmount()+reportResponses.get(i-1).getTotal_amount());
            }

        }

        return reportResponses;
    }

    @Override
    public TransactionAddResponse getRandomTransaction() throws APIException {
        List<TransactionAddResponse> transactionAddResponseList = new ArrayList<>();
        transactionDao.getAll().forEach(transaction ->
                transactionAddResponseList.add(new TransactionAddResponse(transaction)));

        if(transactionAddResponseList.isEmpty()){
            throw new APIException(Constants.NO_REGISTERS);
        }

        return transactionAddResponseList.get(Math.toIntExact(Util.random(transactionAddResponseList.size())));
    }


}