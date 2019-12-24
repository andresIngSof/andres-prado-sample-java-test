package com.sample.test.api.utils;

import com.sample.test.api.dao.impl.TransactionDaoImpl;
import com.sample.test.api.model.Transaction;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReaderUtil {

    private static final Logger LOG = Logger.getLogger(TransactionDaoImpl.class);


    private String pathFile;

    /**
     * Método que lee el fichero linea a linea y construye una lista de todas las transactions
     * @return List<Transaction>
     */
    public List<Transaction> getAllTransactions() {
        Scanner scanner;
        List<Transaction> transactionList = new ArrayList<>();
        try {
            scanner = new Scanner(new File(pathFile));
            while (scanner.hasNextLine()) {
                Scanner lineTransaction = new Scanner(scanner.nextLine());
                lineTransaction.useDelimiter("\\s*\\|\\s*");
                transactionList.add(getTransactionLine(lineTransaction));
            }
            scanner.close();
        }catch (FileNotFoundException e) {
            LOG.error("Error on reading file -> "+ e.getMessage());
        }

        return transactionList;
    }


    /**
     * Método que escribe en el fichero una transaction
     * en una linea del fichero
     * @return Transaction
     */
    public Transaction addLine(Transaction transaction) {
        FileWriter flwriter = null;
        try {
            flwriter = new FileWriter(pathFile, true);
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
            bfwriter.write(processTransaction(transaction)+"\n");
            bfwriter.close();
            LOG.info("Transaction ADD -> "+transaction.toString());
        } catch (IOException e) {
            LOG.error("Error on reading file -> "+ e.getMessage());
        } finally {
            if (flwriter != null) {
                try {
                    flwriter.close();
                } catch (IOException e) {
                    LOG.error("Error on closing file -> "+ e.getMessage());
                }
            }
        }
        return transaction;
    }

    /**
     * Método que busca dentro una transaction por idTransaccion y user_id
     * @return Transaction
     */
    public Transaction getTransactionById(Long user_id, String idTransaction){
        return getAllTransactions().stream().filter(transaction ->
                idTransaction.equals(transaction.getTransaction_id())
                && user_id.equals(transaction.getUser_id()) )
                .findAny().orElse(null);
    }

    /**
     * Método que valida la existencia de una transaction con el mismo id
     * @return boolean
     */
    public boolean existeTransaction(String idTransaction){
        return getAllTransactions().stream().filter(transaction ->
                idTransaction.equals(transaction.getTransaction_id())).findAny().isPresent();
    }


    /**
     * Método que construye un model Transaction através de un objeto Scanner que contiene la lectura
     * de una linea del fichero
     * @return Transaction
     */
    private Transaction getTransactionLine(Scanner lineTransaction){
        Transaction transaction = new Transaction();
        transaction.setTransaction_id(lineTransaction.next());
        transaction.setUser_id(Long.valueOf(lineTransaction.next()));
        transaction.setAmount(new BigDecimal(lineTransaction.next()));
        transaction.setDescription(lineTransaction.next());
        transaction.setDate(LocalDate.parse(lineTransaction.next()));
        return transaction;
    }


    /**
     * Método que genera un id unico para la transaction
     * @return Transaction
     */
    public String generateKeyTransaction(){
        String key = RandomStringUtils.random(8, true, true) +
                "-" + RandomStringUtils.random(4, true, true) +
                "-" + RandomStringUtils.random(4, true, true) +
                "-" + RandomStringUtils.random(4, true, true) +
                "-" + RandomStringUtils.random(12, true, true);
        return key;
    }

    private String processTransaction(Transaction transaction){
        return transaction.getTransaction_id()+"|"+transaction.getUser_id()+"|"
                +transaction.getAmount()+"|"+transaction.getDescription()+"|"+transaction.getDate();
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }
}
