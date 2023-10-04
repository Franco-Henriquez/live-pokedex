package com.example.livepokedex.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.livepokedex.models.Card;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
	List<Card> findAll();

}
