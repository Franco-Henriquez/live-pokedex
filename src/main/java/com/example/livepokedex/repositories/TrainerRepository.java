package com.example.livepokedex.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.livepokedex.models.Card;
import com.example.livepokedex.models.Trainer;

@Repository
public interface TrainerRepository extends CrudRepository<Trainer, Long> {
		Optional<Trainer> findByEmail(String email); //look for availability upon registration
		List<Trainer> findAll();
		Optional<Trainer> findAllByCardList(Card thisCard); //this may not work as intended, need to test 


}
