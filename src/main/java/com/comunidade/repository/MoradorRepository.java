package com.comunidade.repository;

import com.comunidade.model.Morador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoradorRepository extends JpaRepository<Morador, Integer> {
}
