package com.muhammet.bilgefb.repository.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VwUserProfile {
    Long userid;
    String username;
    int followerCount;
    String profileUrl;
}
