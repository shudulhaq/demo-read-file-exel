package com.infosys.demoreadexcel.service;

import com.infosys.demoreadexcel.helper.ExcelHelper;
import com.infosys.demoreadexcel.model.Excel;
import com.infosys.demoreadexcel.repository.ExcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ExcelServiceImp implements ExcelService{

    @Autowired
    ExcelRepository excelRepository;

    @Override
    public void save(MultipartFile file){
    try {
        List<Excel> excelList = ExcelHelper.excampleToExcel(file.getInputStream());
    }
    }
}
