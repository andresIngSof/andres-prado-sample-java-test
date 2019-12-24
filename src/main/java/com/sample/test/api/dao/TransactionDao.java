package com.sample.test.api.dao;

import com.sample.test.api.model.Transaction;

import java.util.List;


/**
 *
 * @author : Andrés Prado Cruz
 * @email : andresss32157@gmail.com
 * @class : TransactionDaoImpl.java
 * Interface DAO para el acceso a los datos en el fichero
 */
public interface TransactionDao {

    /**
     * Método para insertar una linea nueva al fichero con los datos de una transaction
     * @return Transaction
     */
    Transaction create(Transaction transaction);

    /**
     * Método para buscar una transaction a partir del user_id y el idTransaction
     * @return Transaction
     */
    Transaction getById(Long user_id ,String idTransaction);

    /**
     * Método para leer el fichero y recuperar la lista de todas las transactions
     * @return List<Transaction>
     */
    List<Transaction> getAll();

    /**
     * Método para leer el fichero y recuperar la lista de todas las transactions por user_id
     * @return List<Transaction>
     */
    List<Transaction> getAllByUserId(Long user_id);

    /**
     * Método para verificar si existe una transaction en el fichero a aprtir de su idTransaction
     * de encontrarla regresa true
     * @return boolean
     */
    boolean existeRegistro(String idTransaction);


}
