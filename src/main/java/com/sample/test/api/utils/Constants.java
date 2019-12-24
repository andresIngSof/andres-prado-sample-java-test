package com.sample.test.api.utils;

public interface Constants {

    Integer SALT_LENGTH = 40;
    String LIKE = " % ";
    String URL = "[URL]  ====>  ";
    String JSON_RECEIVED = "JSON Received: ";
    String JSON_SEND = "JSON Send: ";
    String QUERY_SEND = "Query Send: ";
    String DATE_PATTERN = "dd-MM-yyyy";


    String ZONA_HORARIA = "America/Mexico_City";


    String NO_REGISTERS = "Transaction not found";
    String NO_ENTITY_FOUND = "No entity found";
    String REQUIRED_PARAM = "El siguiente parametro es requerido: [%s]";
    String USUARIO_NOT_FOUND = "No results found for the user";

    // Codigos de Error:
    Long OK_CODE = 200L;
    Long NO_REGISTERS_CODE = 1204L;
    Long BAD_REQUEST_CODE = 1400L;
    Long INTERNAL_ERROR_CODE = 1500L;



}
