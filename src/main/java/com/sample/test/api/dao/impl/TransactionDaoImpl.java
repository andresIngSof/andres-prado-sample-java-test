package com.sample.test.api.dao.impl;

import com.sample.test.api.dao.TransactionDao;
import com.sample.test.api.model.Transaction;
import com.sample.test.api.utils.FileReaderUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * @author : Andrés Prado Cruz
 * @email : andresss32157@gmail.com
 * @class : TransactionDaoImpl.java
 * Implementacion de la interface TransactionDao
 * @see com.sample.test.api.dao.TransactionDao
 */
@Repository("transactionDao")
public class TransactionDaoImpl implements TransactionDao {

    private static final Logger LOG = Logger.getLogger(TransactionDaoImpl.class);

    @Value("${file.path}")
    private String pathFile;

    @Autowired
    @Qualifier("fileDatasource")
    private FileReaderUtil fileReaderUtil;

    @Override
    public Transaction create(Transaction transaction) {
        transaction.setTransaction_id(fileReaderUtil.generateKeyTransaction());
        while(existeRegistro(transaction.getTransaction_id())){
            transaction.setTransaction_id(fileReaderUtil.generateKeyTransaction());
        }
        return fileReaderUtil.addLine(transaction);
    }

    @Override
    public Transaction getById(Long user_id, String idTransaction) {
        return fileReaderUtil.getTransactionById(user_id, idTransaction);

    }

    @Override
    public List<Transaction> getAll() {
        return fileReaderUtil.getAllTransactions();
    }

    @Override
    public List<Transaction> getAllByUserId(Long user_id) {
        return fileReaderUtil.getAllTransactions().stream().filter(transaction ->
                        user_id.equals(transaction.getUser_id()) ).collect(Collectors.toList());
    }

    @Override
    public boolean existeRegistro(String idTransaction) {
        return fileReaderUtil.existeTransaction(idTransaction);
    }

    public FileReaderUtil getFileReaderUtil() {
        return fileReaderUtil;
    }

    public void setFileReaderUtil(FileReaderUtil fileReaderUtil) {
        this.fileReaderUtil = fileReaderUtil;
    }
}
