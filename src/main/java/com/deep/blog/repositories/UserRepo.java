package com.deep.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deep.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
