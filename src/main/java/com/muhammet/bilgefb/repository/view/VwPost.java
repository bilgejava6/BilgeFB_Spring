package com.muhammet.bilgefb.repository.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Comment;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VwPost {
    Long id;
    Long userid;
    String username;
    String postimage;
    String userprofileimage;
    boolean isfollow;
    String comment;
    List<Comment> comments;
    int likecount;
    int dislikecount;
    String sharedtime;
}
