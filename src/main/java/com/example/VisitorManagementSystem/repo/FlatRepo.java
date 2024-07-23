package com.example.VisitorManagementSystem.repo;

import com.example.VisitorManagementSystem.entity.Flat;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlatRepo extends JpaRepository<Flat,Long> {
    Flat findByNumber(String number);
}
