package com.muhammet.bilgefb.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreatePostRequestDto {
    String token;
    String imageurl;
    Long userid;
    String comment;
}
