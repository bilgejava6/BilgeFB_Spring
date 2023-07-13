package com.muhammet.bilgefb.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateCommentRequestDto {
    String token;
    Long postid;
    Long userid;
    String comment;
}
