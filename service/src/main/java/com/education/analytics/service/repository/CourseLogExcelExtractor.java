package com.education.analytics.service.repository;

import com.education.analytics.model.domain.Activity;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.*;

public class CourseLogExcelExtractor implements ICourseLogRepository {

    private final String dataSourcePath;

    public CourseLogExcelExtractor(String dataSourcePath) {
        this.dataSourcePath = dataSourcePath;
    }

    @Override
    public List<Activity> get() {
        return getDataSheetRows() //
                .stream() //
                .map(this::rowToCourseLog)
                .collect(toList());
    }

    private List<XSSFRow> getDataSheetRows() {
        try {
            List<XSSFRow> rows = new ArrayList<XSSFRow>();

            FileInputStream excelFile = new FileInputStream(new File(dataSourcePath));
            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);

            for (int i = 1; i < workbook.getSheetAt(0).getPhysicalNumberOfRows(); i++) {
                rows.add(workbook.getSheetAt(0).getRow(i));
            }
            return rows;
        } catch (Exception e) {
            //TODO Add logger here
            throw new RuntimeException(e);
        }
    }

    private Activity rowToCourseLog(XSSFRow row) {
        return new Activity(//
                row.getCell(0).getStringCellValue(),
                row.getCell(1).getStringCellValue(),
                row.getCell(2).getStringCellValue(),
                row.getCell(3).getStringCellValue(),
                row.getCell(4).getStringCellValue()
        );
    }
}
