package com.springboot.bloggingApp.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Role {

	@Id
	private int id;

	private String name;
}