package com.muhammet.bilgefb.dto.response;

import com.muhammet.bilgefb.repository.view.VwUserProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetProfileByTokenResponseDto {
    int statusCode;
    String message;
    VwUserProfile data;
}
