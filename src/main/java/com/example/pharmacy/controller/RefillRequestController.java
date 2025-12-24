package com.example.pharmacy.controller;

import com.example.pharmacy.model.RefillRequest;
import com.example.pharmacy.repository.RefillRequestRepository;
import org.springframework.web.bind.annotation.*;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

import java.util.List;


import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/refills")
public class RefillRequestController {

    private final RefillRequestRepository repository;

    public RefillRequestController(RefillRequestRepository repository) {
        this.repository = repository;
    }

    // POST: Submit a new refill request
    @PostMapping
    public Long submitRefill(@RequestBody RefillRequest refillRequest) {

        refillRequest.setTimestamp(LocalDateTime.now());

        RefillRequest saved = repository.save(refillRequest);

        return saved.getId();
    }

    // GET: Return all refill requests for admin
    @GetMapping("/all")
    public List<RefillRequest> getAllRefills() {
        return repository.findAll();
    }

    @GetMapping("/export")
    public void exportToPdf(HttpServletResponse response) throws Exception {

        response.setContentType("application/pdf");
        response.setHeader(
                "Content-Disposition",
                "attachment; filename=refill_requests.pdf"
        );

        List<RefillRequest> requests = repository.findAll();

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // ===== Title =====
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph title = new Paragraph("Pharmacy Refill Requests", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        // ===== Table =====
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1f, 3f, 3f, 4f});

        addTableHeader(table);
        addTableData(table, requests);

        document.add(table);
        document.close();
    }

    private void addTableHeader(PdfPTable table) {
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Phrase("ID", headerFont));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Name", headerFont));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Phone", headerFont));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Prescription", headerFont));
        table.addCell(cell);
    }

    private void addTableData(PdfPTable table, List<RefillRequest> requests) {
        for (RefillRequest r : requests) {
            table.addCell(String.valueOf(r.getId()));
            table.addCell(r.getName());
            table.addCell(r.getPhone());
            table.addCell(r.getPrescription());
        }
    }


}
