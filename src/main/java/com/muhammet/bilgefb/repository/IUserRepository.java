package com.muhammet.bilgefb.repository;

import com.muhammet.bilgefb.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository  extends JpaRepository<User,Long> {
}
