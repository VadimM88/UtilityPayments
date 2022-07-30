package com.example.legalreportviewer.service;

import com.example.legalreportviewer.entity.LegalPerson;
import com.example.legalreportviewer.repository.LegalPersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LegalPersonService {
    private final LegalPersonRepository repository;

    public LegalPersonService(LegalPersonRepository repository) {
        this.repository = repository;
    }


}