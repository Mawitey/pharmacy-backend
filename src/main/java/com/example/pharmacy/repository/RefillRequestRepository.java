package com.example.pharmacy.repository;

import com.example.pharmacy.model.RefillRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefillRequestRepository extends JpaRepository<RefillRequest, Long> {
}
