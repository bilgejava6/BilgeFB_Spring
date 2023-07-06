package com.muhammet.bilgefb.services;

import com.muhammet.bilgefb.repository.ICommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommnetService {
    private final ICommentRepository repository;

}
