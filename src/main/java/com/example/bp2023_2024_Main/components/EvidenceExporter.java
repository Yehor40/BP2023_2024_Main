package com.example.bp2023_2024_Main.components;

import com.example.bp2023_2024_Main.entity.Evidence;
import com.example.bp2023_2024_Main.service.EvidenceService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
@Component
public class EvidenceExporter {
    @Autowired
    private EvidenceService evidenceService;
    // Export single evidence to Excel file by ID
    public void exportSingleEvidenceToExcel(Long evidenceId, String filePath) {
        Evidence evidence = evidenceService.getEvidenceById(evidenceId).orElseThrow();
        if (evidence != null) {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Evidence");

            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Task Name", "Project", "Order Num", "Department", "Est Time", "Time Spent", "Charge", "Total", "State", "Month"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            Row row = sheet.createRow(1);
            row.createCell(0).setCellValue(evidence.getId());
            row.createCell(1).setCellValue(evidence.getTaskName());
            row.createCell(2).setCellValue(evidence.getProject());
            row.createCell(3).setCellValue(evidence.getOrderNum());
            row.createCell(4).setCellValue(evidence.getDepartment());
            row.createCell(5).setCellValue(evidence.getEstTime());
            row.createCell(6).setCellValue(evidence.getTimeSpent());
            row.createCell(7).setCellValue(evidence.getCharge());
            row.createCell(8).setCellValue(evidence.getTotal());
            row.createCell(9).setCellValue(evidence.getState());
            row.createCell(10).setCellValue(evidence.getMonth());

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Evidence with ID " + evidenceId + " not found.");
        }
    }

    // Export all evidences to Excel file
    public void exportAllEvidencesToExcel(String filePath) {
        List<Evidence> evidenceList = evidenceService.getAllEvidence();
        if (evidenceList != null && !evidenceList.isEmpty()) {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("All Evidence");

            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Task Name", "Project", "Order Num", "Department", "Est Time", "Time Spent", "Charge", "Total", "State", "Month"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            int rowNum = 1;
            for (Evidence evidence : evidenceList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(evidence.getId());
                row.createCell(1).setCellValue(evidence.getTaskName());
                row.createCell(2).setCellValue(evidence.getProject());
                row.createCell(3).setCellValue(evidence.getOrderNum());
                row.createCell(4).setCellValue(evidence.getDepartment());
                row.createCell(5).setCellValue(evidence.getEstTime());
                row.createCell(6).setCellValue(evidence.getTimeSpent());
                row.createCell(7).setCellValue(evidence.getCharge());
                row.createCell(8).setCellValue(evidence.getTotal());
                row.createCell(9).setCellValue(evidence.getState());
                row.createCell(10).setCellValue(evidence.getMonth());
            }

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No evidences found.");
        }
    }

}
