package com.muhammet.bilgefb.repository;

import com.muhammet.bilgefb.repository.entity.DisLike;
import com.muhammet.bilgefb.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDisLikeRepository   extends JpaRepository<DisLike,Long> {
}
