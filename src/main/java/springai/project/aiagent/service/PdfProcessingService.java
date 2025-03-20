package springai.project.aiagent.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import springai.project.aiagent.entities.PdfDocument;
import springai.project.aiagent.repo.PdfDocumentRepository;

import java.io.IOException;
import java.util.List;

@Service
public class PdfProcessingService {

    private final PdfDocumentRepository pdfDocumentRepository;
    private final EmbeddingService embeddingService;

    public PdfProcessingService(PdfDocumentRepository pdfDocumentRepository, EmbeddingService embeddingService) {
        this.pdfDocumentRepository = pdfDocumentRepository;
        this.embeddingService = embeddingService;
    }

    public PdfDocument savePdf(MultipartFile file) throws IOException {
        byte[] fileData = file.getBytes();
        String text = extractTextFromPdf(file);
        float[] embedding = embeddingService.generateEmbedding(text);

        PdfDocument pdfDocument = new PdfDocument();
        pdfDocument.setFileName(file.getOriginalFilename());
        pdfDocument.setFileData(fileData);
        pdfDocument.setEmbedding(embedding);

        return pdfDocumentRepository.save(pdfDocument);
    }

    private String extractTextFromPdf(MultipartFile file) throws IOException {
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            return pdfStripper.getText(document);
        }
    }
}
