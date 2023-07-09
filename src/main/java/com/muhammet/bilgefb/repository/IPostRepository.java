package com.muhammet.bilgefb.repository;

import com.muhammet.bilgefb.repository.entity.Post;
import com.muhammet.bilgefb.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository   extends JpaRepository<Post,Long> {
    List<Post> findAllByOrderBySharedtimeDesc();
}
