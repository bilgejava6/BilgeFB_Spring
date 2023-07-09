package com.muhammet.bilgefb.repository;

import com.muhammet.bilgefb.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository  extends JpaRepository<User,Long> {
    Optional<User> findOptionalByUsername(String username);

    Optional<User> findOptionalByUsernameAndPassword(String username, String password);

    List<User> findTop5ByOrderByMemberdateDesc();
}
