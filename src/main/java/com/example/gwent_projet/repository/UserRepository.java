package com.example.gwent_projet.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gwent_projet.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>  {
	List<User> findAll();
}
