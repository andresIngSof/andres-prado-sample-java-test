package com.sample.test.api.exception;

import java.math.BigInteger;
import java.util.Objects;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.sample.test.api.utils.Constants;

/**
 *
 * @author : Andrés Prado Cruz
 * @email : andresss32157@gmail.com
 * @class : APIException.java
 * @description : Clase para manejo de excepciones
 */
public class APIException extends Exception {

	private static final long serialVersionUID = 1L;

	private final String message;
	private final Long code;

	public APIException(String msg) {
		message = msg;
		code = setCode(message);
	}
	
	public APIException(Throwable e) {
		message = setMessage(e);
		code = setCode(message);
	}

	private String setMessage(Throwable e) {
		Throwable rootCause = ExceptionUtils.getRootCause(e);
		if(rootCause == null) {
			return e.getMessage();
		} else {
			return rootCause.getMessage();
		}
	}
	
	private Long setCode(String msg) {
		if(Objects.isNull(msg)) {
			return Constants.INTERNAL_ERROR_CODE;
		}
		if(msg.contains(Constants.NO_REGISTERS)
				|| msg.contains(Constants.NO_ENTITY_FOUND)
				|| msg.contains(Constants.USUARIO_NOT_FOUND))
			{
			return Constants.NO_REGISTERS_CODE;
		}
		if(msg.contains(Constants.REQUIRED_PARAM.substring(BigInteger.ZERO.intValue(), Constants.REQUIRED_PARAM.length() - BigInteger.TEN.intValue()))) {
			return Constants.BAD_REQUEST_CODE;
		}
		return Constants.INTERNAL_ERROR_CODE;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public Long getCode() {
		return code;
	}

}
