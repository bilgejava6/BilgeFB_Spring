package com.muhammet.bilgefb.services;

import com.muhammet.bilgefb.dto.request.CreatePostRequestDto;
import com.muhammet.bilgefb.dto.request.DefaultRequestDto;
import com.muhammet.bilgefb.dto.response.CreatePostResponseDto;
import com.muhammet.bilgefb.dto.response.GetAllPostsResponseDto;
import com.muhammet.bilgefb.exceptions.BilgeFbException;
import com.muhammet.bilgefb.exceptions.ErrorType;
import com.muhammet.bilgefb.repository.IPostRepository;
import com.muhammet.bilgefb.repository.IUserRepository;
import com.muhammet.bilgefb.repository.entity.Post;
import com.muhammet.bilgefb.repository.entity.User;
import com.muhammet.bilgefb.repository.view.VwPost;
import com.muhammet.bilgefb.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private  final IPostRepository repository;
    private final IUserRepository userRepository;
    private final JwtTokenManager jwtTokenManager;
    public CreatePostResponseDto createPost(CreatePostRequestDto dto) {
        Optional<Long> userId = jwtTokenManager.getByIdFromToken(dto.getToken());
        if(userId.isEmpty())
            throw new BilgeFbException(ErrorType.INVALID_TOKEN);
        Post post = Post.builder()
                .userid(userId.get())
                .comment(dto.getComment())
                .imageurl(dto.getImageurl())
                .sharedtime(System.currentTimeMillis())
                .build();
        repository.save(post);
        return CreatePostResponseDto.builder()
                .message("Post başarıyla oluşturuldu")
                .statusCode(200)
                .build();
    }

    public GetAllPostsResponseDto getAllPosts(DefaultRequestDto dto) {
        Optional<Long> userId = jwtTokenManager.getByIdFromToken(dto.getToken());
        if(userId.isEmpty())
            throw new BilgeFbException(ErrorType.INVALID_TOKEN);
        List<Post> postList = repository.findAllByOrderBySharedtimeDesc();
        /**
         * Bir liste içindeki herhangi bir alana ait bilgi listesini almak için kullanılır.
         */
        List<Long> userids = postList.stream().map(Post::getUserid).toList();
        List<User> userList = userRepository.findAllById(userids);
        List<VwPost> vwPostList = new ArrayList<>();
        postList.forEach(post->{
            Optional<User> user = userList.stream().filter(u->u.getId().equals(post.getUserid())).findFirst();
            user.ifPresent(u->{
                vwPostList.add(
                        VwPost.builder()
                                .id(post.getId())
                                .dislikecount(post.getDislikecount())
                                .likecount(post.getLikecount())
                                .comment(post.getComment())
                                .userid(post.getUserid())
                                .postimage(post.getImageurl())
                                .userprofileimage(u.getUserimage())
                                .username(u.getUsername())
                                .sharedtime(new Date(post.getSharedtime()).toString())
                                .build()
                );
            });

        });
        return GetAllPostsResponseDto.builder()
                .message("Postlar başarıyla getirildi")
                .statusCode(200)
                .data(vwPostList)
                .build();
    }
}
