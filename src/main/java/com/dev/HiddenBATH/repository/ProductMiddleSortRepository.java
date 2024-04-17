package com.dev.HiddenBATH.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dev.HiddenBATH.model.product.MiddleSort;

@Repository
public interface ProductMiddleSortRepository extends JpaRepository<MiddleSort, Long>{
	
	@Query("SELECT MAX(middleSortIndex) FROM MiddleSort")
	Optional<Integer> findFirstIndex();

}
