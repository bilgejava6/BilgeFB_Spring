package com.muhammet.bilgefb.services;

import com.muhammet.bilgefb.repository.IFollowingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowingService {
    private final IFollowingRepository repository;
}
