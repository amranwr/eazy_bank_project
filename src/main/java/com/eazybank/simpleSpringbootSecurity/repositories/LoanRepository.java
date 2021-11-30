package com.eazybank.simpleSpringbootSecurity.repositories;

import java.util.List;

import com.eazybank.simpleSpringbootSecurity.models.Loans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoanRepository extends CrudRepository<Loans, Long> {
	
	List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);

}
