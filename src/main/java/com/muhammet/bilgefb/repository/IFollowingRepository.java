package com.muhammet.bilgefb.repository;

import com.muhammet.bilgefb.repository.entity.Following;
import com.muhammet.bilgefb.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFollowingRepository   extends JpaRepository<Following,Long> {
}
