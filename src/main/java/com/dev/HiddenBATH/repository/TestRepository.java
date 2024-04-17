package com.dev.HiddenBATH.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.HiddenBATH.model.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long>{

}
