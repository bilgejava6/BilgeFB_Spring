package com.muhammet.bilgefb.services;

import com.muhammet.bilgefb.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository repository;
}
