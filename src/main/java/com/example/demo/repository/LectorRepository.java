package com.example.demo.repository;

import com.example.demo.model.Lector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectorRepository extends JpaRepository<Lector, Long> {
    List<Lector> getLectorsByFirstNameContainingIgnoreCase(String s);
    List<Lector> getLectorsByLastNameContainingIgnoreCase(String s);

}
