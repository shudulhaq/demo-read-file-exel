package com.infosys.demoreadexcel.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ExcelService {
    void save(MultipartFile file);
    List<Excel> getAllExcel();
    ByteArrayInputStream load();
}
