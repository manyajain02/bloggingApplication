package com.springboot.bloggingApp.repository;


import com.springboot.bloggingApp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo  extends JpaRepository<Role, Integer> {

}