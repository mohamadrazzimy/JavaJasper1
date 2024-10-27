package com.example;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.*;

public class ReportGenerator {

    public static void main(String[] args) {
        // Sample data for the report
        List<ReportData> dataList = new ArrayList<>();
        dataList.add(new ReportData("John Doe", 29));
        dataList.add(new ReportData("Jane Smith", 34));
        dataList.add(new ReportData("Mike Johnson", 45));

        // Create a data source
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

        // Load the Jasper report from .jrxml file
        try {
            // Adjust the path if necessary
            JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/report_template.jrxml");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);

            // Ensure the output directory exists
            String outputDir = "output/";
            new java.io.File(outputDir).mkdirs(); // Create output directory if it doesn't exist

            // Export the report to a PDF file
            JasperExportManager.exportReportToPdfFile(jasperPrint, outputDir + "report.pdf");

            System.out.println("Report generated successfully!");

        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}

