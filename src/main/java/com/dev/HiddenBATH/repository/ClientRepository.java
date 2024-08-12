package com.dev.HiddenBATH.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.HiddenBATH.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
