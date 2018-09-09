package com.snomyc.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snomyc.sys.bean.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {

    public List<User> findByOrderByGroupNumAsc();
}