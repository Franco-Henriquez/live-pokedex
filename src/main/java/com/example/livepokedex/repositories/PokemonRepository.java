package com.example.livepokedex.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.livepokedex.models.Pokemon;

@Repository
public interface PokemonRepository extends CrudRepository<Pokemon, Long> {
	List<Pokemon> findAll();
	Optional<Pokemon> findByDexId(Long dexid); //look for availability upon registration


}
