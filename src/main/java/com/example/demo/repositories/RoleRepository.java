package com.example.demo.repositories;

import com.example.demo.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
