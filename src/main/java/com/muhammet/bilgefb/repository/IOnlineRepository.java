package com.muhammet.bilgefb.repository;

import com.muhammet.bilgefb.repository.entity.Online;
import com.muhammet.bilgefb.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOnlineRepository   extends JpaRepository<Online,Long> {
    Optional<Online> findOptionalByUserid(Long id);

    List<Online> findAllByIsonlineTrue();

    @Query("select o.userid from Online o where o.isonline = true")
    List<Long> getAllOnlineUserIds();

}
