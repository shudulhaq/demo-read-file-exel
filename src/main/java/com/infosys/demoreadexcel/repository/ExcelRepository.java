package com.infosys.demoreadexcel.repository;

import com.infosys.demoreadexcel.model.Excel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExcelRepository extends JpaRepository<Excel, Long> {
}
