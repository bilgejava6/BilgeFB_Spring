package com.muhammet.bilgefb.services;

import com.muhammet.bilgefb.repository.IPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private  final IPostRepository repository;
}
