package springai.project.aiagent.controller;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springai.project.aiagent.entities.PdfDocument;
import springai.project.aiagent.service.PdfProcessingService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@RestController
@RequestMapping("/api/pdf")
public class DocumentController {

    private final PdfProcessingService pdfProcessingService;

    public DocumentController(PdfProcessingService pdfProcessingService) {
        this.pdfProcessingService = pdfProcessingService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPdf(@RequestParam("file") MultipartFile file) {
        try {
            PdfDocument pdfDocument = pdfProcessingService.savePdf(file);
            return ResponseEntity.ok("Fichier PDF enregistré avec l'ID: " + pdfDocument.getId());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Erreur lors du traitement du fichier.");
        }
    }
}