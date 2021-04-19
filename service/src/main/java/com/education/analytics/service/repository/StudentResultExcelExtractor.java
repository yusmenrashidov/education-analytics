package com.education.analytics.service.repository;

import com.education.analytics.model.domain.StudentResult;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.*;

public class StudentResultExcelExtractor implements IStudentResultRepository {

    private final String dataSourcePath;

    public StudentResultExcelExtractor(String dataSourcePath) {
        this.dataSourcePath = dataSourcePath;
    }

    @Override
    public List<StudentResult> get() {
        return getDataSheetRows() //
                .stream() //
                .map(this::rowToStudentResult) //
                .collect(toList());
    }

    private List<XSSFRow> getDataSheetRows() {
        try {
            List<XSSFRow> rows = new ArrayList<XSSFRow>();

            FileInputStream excelFile = new FileInputStream(new File(dataSourcePath));
            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);

            int d = workbook.getSheetAt(0).getLastRowNum();

            for (int i = 1; i < workbook.getSheetAt(0).getPhysicalNumberOfRows(); i++) {
                rows.add(workbook.getSheetAt(0).getRow(i));
            }
            return rows;
        } catch (Exception e) {
            //TODO Add logger here
            throw new RuntimeException(e);
        }
    }

    private StudentResult rowToStudentResult(XSSFRow row) {
        return new StudentResult(
                Objects.isNull(row.getCell(0).getNumericCellValue()) ? null : row.getCell(0).getNumericCellValue(),
                Objects.isNull(row.getCell(1).getNumericCellValue()) ? null : row.getCell(1).getNumericCellValue()
                );
    }
}
