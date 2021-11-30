package com.eazybank.simpleSpringbootSecurity.repositories;

import java.util.List;

import com.eazybank.simpleSpringbootSecurity.models.Cards;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CardsRepository extends CrudRepository<Cards, Long> {
	
	List<Cards> findByCustomerId(int customerId);

}
