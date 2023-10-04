package com.example.livepokedex.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.livepokedex.models.SearchHistory;

@Repository
public interface SearchHistoryRepository extends CrudRepository<SearchHistory, Long> {
	List<SearchHistory> findAll();
}
