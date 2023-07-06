package com.muhammet.bilgefb.services;

import com.muhammet.bilgefb.repository.IDisLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DisLikeService {
    private final IDisLikeRepository repository;
}
