package com.example.gwent_projet.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.gwent_projet.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, UUID>  {

}
