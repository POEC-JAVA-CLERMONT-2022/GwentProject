package com.example.gwent_projet.repository;

import com.example.gwent_projet.entity.user.ERole;
import com.example.gwent_projet.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
