package com.example.gwent_projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gwent_projet.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
	

}
