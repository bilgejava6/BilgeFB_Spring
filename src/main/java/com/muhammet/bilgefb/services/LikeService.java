package com.muhammet.bilgefb.services;

import com.muhammet.bilgefb.repository.ILikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private  final ILikeRepository repository;
}
