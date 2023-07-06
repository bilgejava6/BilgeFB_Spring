package com.muhammet.bilgefb.services;

import com.muhammet.bilgefb.repository.IFollowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowerService {
    private final IFollowerRepository repository;
}
