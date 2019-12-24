package com.sample.test.api.test;

import com.sample.test.api.dao.impl.TransactionDaoImpl;
import com.sample.test.api.model.Transaction;
import com.sample.test.api.request.TransactionAddRequest;
import com.sample.test.api.utils.FileReaderUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class TransactionServiceRest {


	private TransactionDaoImpl transactionDao;


	@Before
	public void setUp() throws Exception {
		transactionDao = new TransactionDaoImpl();
		FileReaderUtil fileReaderUtil = new FileReaderUtil();
		fileReaderUtil.setPathFile("src/main/resources/dataTest.txt");
		transactionDao.setFileReaderUtil(fileReaderUtil);
	}

	@Test
	public void addTransaction() throws Exception {
		TransactionAddRequest transactionAddRequest = new TransactionAddRequest();
		transactionAddRequest.setDate(LocalDate.now());
		transactionAddRequest.setAmount(new BigDecimal("595"));
		transactionAddRequest.setUser_id(5555L);
		transactionAddRequest.setDescription("Wings Army");
		assertTrue(transactionDao.create(new Transaction(transactionAddRequest)) instanceof  Transaction);
	}

	@Test
	public void getById() throws Exception {
		Long user_id = 5555l;
		String keyTransaction = "yWNtzm9A-Uyar-Zp2I-F0t8-wMFGVN6udzvE";
		Transaction transaction = transactionDao.getById(user_id,keyTransaction);
		assertEquals(keyTransaction, transaction.getTransaction_id());
		assertEquals(user_id, transaction.getUser_id());
	}

	@Test
	public void getAll() throws Exception {
		List<Transaction> transactionList = transactionDao.getAll();
		assertFalse(transactionList.isEmpty());
	}

	@Test
	public void getAllByUserId() throws Exception {
		Long user_id = 5555l;
		List<Transaction> transactionList = transactionDao.getAllByUserId(user_id);
		assertFalse(transactionList.isEmpty());
		assertEquals(transactionList.stream().findAny().orElse(null).getUser_id(), user_id);
	}

	@Test
	public void existeRegistro() throws Exception {
		String keyTransaction = "yWNtzm9A-Uyar-Zp2I-F0t8-wMFGVN6udzvE";
		boolean existeRegistro = transactionDao.existeRegistro(keyTransaction);
		assertTrue(existeRegistro);
	}

}