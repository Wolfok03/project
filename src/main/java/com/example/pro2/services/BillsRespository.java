package com.example.pro2.services;

import com.example.pro2.Model.bills.bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillsRespository extends JpaRepository<bill, Integer> {
}
