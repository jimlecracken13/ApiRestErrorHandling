package com.example.ApiRestErrorHandling.repositories;

import com.example.ApiRestErrorHandling.entities.Victime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface VictimeRepository extends JpaRepository<Victime, Integer> {
    Victime findByNumerotel(String numero_tel);
}
