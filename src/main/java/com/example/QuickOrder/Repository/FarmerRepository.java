package com.example.QuickOrder.Repository;

import com.example.QuickOrder.Entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Integer> {
    Farmer findByPhoneNo(String phoneNo);
}
