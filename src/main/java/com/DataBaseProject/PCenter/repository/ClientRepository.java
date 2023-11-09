package com.DataBaseProject.PCenter.repository;

import com.DataBaseProject.PCenter.data.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
