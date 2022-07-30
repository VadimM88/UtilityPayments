package com.example.legalreportviewer.repository;

import com.example.legalreportviewer.entity.LegalPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LegalPersonRepository  extends JpaRepository<LegalPerson, Integer> {
    Optional<LegalPerson> findByInn(String inn);
    Optional<LegalPerson> findById(Integer id);
}
