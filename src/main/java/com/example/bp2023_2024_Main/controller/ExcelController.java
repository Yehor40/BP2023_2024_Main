package com.example.bp2023_2024_Main.controller;

import com.example.bp2023_2024_Main.components.EvidenceExporter;
import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ExcelController {
    @Autowired
    private EvidenceExporter evidenceExporter;

    // Controller method to download single evidence Excel file by ID
    @GetMapping("/evidence/download/{id}")
    public ResponseEntity<Resource> downloadSingleEvidenceExcel(@PathVariable Long id) {
        String filePath = "evidence"+id+".xlsx"; // Adjust the file path
        evidenceExporter.exportSingleEvidenceToExcel(id, filePath);

        Resource file = new FileSystemResource(filePath);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    // Controller method to download all evidences Excel file
    @GetMapping("/evidence/download/all")
    public ResponseEntity<Resource> downloadAllEvidencesExcel() {
        String filePath = "evidenceAll.xlsx"; // Adjust the file path
        evidenceExporter.exportAllEvidencesToExcel(filePath);

        Resource file = new FileSystemResource(filePath);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

}
