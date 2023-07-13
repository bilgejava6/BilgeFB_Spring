package com.muhammet.bilgefb.repository.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VwComment {
    Long id;
    String profileurl;
    String username;
    String comment;
}
