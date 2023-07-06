package com.muhammet.bilgefb.services;

import com.muhammet.bilgefb.repository.IOnlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OnLineService {
    private final IOnlineRepository repository;

}
