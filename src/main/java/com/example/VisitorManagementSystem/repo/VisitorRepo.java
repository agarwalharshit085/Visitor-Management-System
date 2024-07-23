package com.example.VisitorManagementSystem.repo;

import com.example.VisitorManagementSystem.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepo extends JpaRepository<Visitor,Long> {

    Visitor findByIdNumber(String idNumber);
}
