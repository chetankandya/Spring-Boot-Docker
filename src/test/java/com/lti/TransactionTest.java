package com.lti;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.lti.model.Transaction;
import com.lti.controllers.TransactionController;
import com.lti.service.TransactionService;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnit4.class)
class TransactionTest {

	@InjectMocks
	TransactionController transactionController;

	@Mock
	TransactionService transactionService;

	@Test
	public void testAddEmployee() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Transaction transaction = new Transaction();
		transaction.setAmount(100);
		transaction.setName("Swanandesh K");
		transaction.setFromAccount("Swanandesh");
		transaction.setToAccount("Manish");
		transaction.setTransactionId(201);

		Transaction transaction1 = new Transaction();
		transaction1.setAmount(100);
		transaction.setFromAccount("Manish B");
		transaction1.setFromAccount("Swanandesh");
		transaction1.setToAccount("Manish");
		transaction1.setTransactionId(202);

		List<Transaction> lis = new ArrayList<Transaction>();
		lis.add(transaction);
		lis.add(transaction1);
		when(transactionService.getTransactionHistory()).thenReturn(getIterableFromIterator(lis.iterator()));

		Iterable<Transaction> itrable = transactionController.viewAllTransactions();

		Iterator<Transaction> iter = itrable.iterator();
		while (iter.hasNext()) {
			Transaction transa = (Transaction) iter.next();
			assertThat(transa.getToAccount()).isEqualTo("Manish");
		}

	}

	// Function to get the Spliterator
	public static <T> Iterable<T> getIterableFromIterator(Iterator<T> iterator) {
		return new Iterable<T>() {
			@Override
			public Iterator<T> iterator() {
				return iterator;
			}
		};
	}
}
