package com.dev.HiddenBATH.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dev.HiddenBATH.model.product.BigSort;

@Repository
public interface ProductBigSortRepository extends JpaRepository<BigSort, Long>{

	@Query("SELECT MAX(bigSortIndex) FROM BigSort")
	Optional<Integer> findFirstIndex();
}
