package com.muhammet.bilgefb.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tblfollowing")
public class Following {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    /**
     * takip eden kişi
     */
    Long userid;
    /**
     * takip edilen kişi
     */
    Long followerid;
    Long time;
}
