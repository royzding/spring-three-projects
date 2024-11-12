package com.sample.microservices.employee.service;


import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

@Service
public class PdfService {
	
//////////////////generatePdf//////////////////////
	
    public byte[] generatePdf() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Hello, World!"));

        document.close();

        return outputStream.toByteArray();
    }	

//////////////////createPdf//////////////////////    
    
    public byte[] createPdf() {
    	
        List<String[]> tableData = Arrays.asList(
                new String[]{"1", "First row data"},
                new String[]{"2", "Second row data"},
                new String[]{"3", "Third row data", "third desc"},
                new String[]{"4", "Fourth row data"},
                new String[]{"5", "fifth row data", "fifth desc"}
        );
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Custom font
            PdfFont font = PdfFontFactory.createFont(); //.createFont("fonts/arial.ttf", "Identity-H", null, true);
            document.setFont(font);

            // Adding header
            addHeader(document, "Advanced PDF Report");

            // Adding dynamic table
            addTable(document, 3, tableData);

            // Adding footer
            addFooter(document);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }

    private void addHeader(Document document, String headerText) {
        Paragraph header = new Paragraph(headerText)
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(16)
                .setBold();
        document.add(header);
    }

    private void addFooter(Document document) {
        Paragraph footer = new Paragraph("Pdf Testing, Roy\ninfo@xyz.com")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(10)
                .setFixedPosition(30, 30, UnitValue.createPercentValue(100));
        document.add(footer);
    }

    private void addTable(Document document, int columnSize, List<String[]> tableData) {
        //float[] columnWidths = {1, 5};
        //Table table = new Table(columnWidths);
        Table table = new Table(columnSize, true);

        // Adding table headers
        table.addHeaderCell(createStyledCellLigtGrey("ID"));
        table.addHeaderCell(createStyledCellYellow("Row Info"));
        table.addHeaderCell(createStyledCellLigtGrey("Description"));

        // Adding table rows
        for (String[] rowData : tableData) {
        	
        	for(int i=0; i<columnSize; i++) {
        		
        		String value = "";
        		
        		if(i <rowData.length) {
        			value = rowData[i];
        		}

           		if(i % 2 == 0) {
                    table.addCell(createStyledCellLigtGrey(value));        			
          		} else {
                    table.addCell(createStyledCellYellow(value));
           		}        			
        	}
        }
        
        document.add(table);
    }

    private Cell createStyledCellLigtGrey(String content) {
        return new Cell().add(new Paragraph(content))
                .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                .setTextAlignment(TextAlignment.CENTER);
    }
    
    private Cell createStyledCellYellow(String content) {
        return new Cell().add(new Paragraph(content))
                .setBackgroundColor(ColorConstants.YELLOW)
                .setTextAlignment(TextAlignment.CENTER);
    }
    
//////////////////createPdf//////////////////////  
    
}

