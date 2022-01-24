package com.openclassrooms.patient.repository;

import com.openclassrooms.patient.entity.Rdv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RdvRepository extends JpaRepository<Rdv,Long> {}
