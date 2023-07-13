package com.muhammet.bilgefb.services;

import com.muhammet.bilgefb.dto.request.CreateCommentRequestDto;
import com.muhammet.bilgefb.dto.response.CreateCommentResponseDto;
import com.muhammet.bilgefb.repository.ICommentRepository;
import com.muhammet.bilgefb.repository.IUserRepository;
import com.muhammet.bilgefb.repository.entity.Comment;
import com.muhammet.bilgefb.repository.entity.User;
import com.muhammet.bilgefb.repository.view.VwComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommnetService {
    private final ICommentRepository repository;
    private final IUserRepository userRepository;
    public List<VwComment> findAllByPostId(Long postid) {
        List<Comment> comments = repository.findAllByPostidOrderByTimeDesc(postid);
        List<VwComment> commentList = new ArrayList<>();
        /*
        ÖNEMLİ!!!!!
        yorum listesi çoooooooooooook uzun olabilir. Bu nedenle yorumlarin
        içinde kullanıcı id si kullanılarak DB den sorgu yapmak çok sakıncalıdır.
        1ms(5-10ms) -> 1.000 => 1sn -> 20.000 kişi => 20sn
        ÖNERİ:
        yorum listesi içinde bulunan kullanıcıların id lerini kullaarak
        yorum yapabları buluyoruz ve bu listede sorgulama ypıyoruz.
         */
        List<Long> userids = comments.stream().map(Comment::getUserid).toList();
        List<User> users = userRepository.findAllById(userids);
        comments.forEach(c->{
            Optional<User> user = users.stream().filter(u->u.getId().equals(c.getUserid())).findFirst();
            user.ifPresent(u->{
                commentList.add(
                        VwComment.builder()
                                .id(c.getId())
                                .comment(c.getComment())
                                .profileurl(u.getUserimage()==null ? "/images/users/user-1.jpg" : u.getUserimage())
                                .username(u.getUsername())
                                .build()
                );
            });

        });
        return commentList;
    }

    public CreateCommentResponseDto createComment(CreateCommentRequestDto dto) {
        repository.save(
                Comment.builder()
                        .comment(dto.getComment())
                        .postid(dto.getPostid())
                        .userid(dto.getUserid())
                        .time(System.currentTimeMillis())
                        .build()
        );
        return CreateCommentResponseDto.builder().message("Başarılı").statusCode(200).build();
    }
}
