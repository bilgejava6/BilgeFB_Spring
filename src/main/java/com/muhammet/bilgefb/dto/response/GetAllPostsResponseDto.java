package com.muhammet.bilgefb.dto.response;

import com.muhammet.bilgefb.repository.view.VwPost;
import com.muhammet.bilgefb.repository.view.VwUserProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetAllPostsResponseDto {
    private Integer statusCode;
    private String message;
    private List<VwPost> data;
}
