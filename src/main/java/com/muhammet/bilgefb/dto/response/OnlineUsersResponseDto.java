package com.muhammet.bilgefb.dto.response;

import com.muhammet.bilgefb.repository.entity.User;
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
public class OnlineUsersResponseDto {
    int statusCode;
    String message;
    List<VwUserProfile> data;
}
