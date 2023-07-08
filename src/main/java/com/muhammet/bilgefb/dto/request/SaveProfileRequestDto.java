package com.muhammet.bilgefb.dto.request;

import com.muhammet.bilgefb.repository.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SaveProfileRequestDto {
    String token;
    User user;
}
