package com.muhammet.bilgefb.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tblpost")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long userid;
    String comment;
    String imageurl;
    int likecount;
    int dislikecount;
    int commentcount;
    Long sharedtime;
    @Transient
    boolean isfollow;
    int state;
}
