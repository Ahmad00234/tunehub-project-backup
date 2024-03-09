package com.Kodnest.tunehub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Kodnest.tunehub.entity.User;

public interface UserRepository extends JpaRepository<User,String > {

    public User findByEmail(String email);

}
