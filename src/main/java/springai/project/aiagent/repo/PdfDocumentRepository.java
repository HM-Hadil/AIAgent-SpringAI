package springai.project.aiagent.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springai.project.aiagent.entities.PdfDocument;

import java.util.List;

@Repository
public interface PdfDocumentRepository extends JpaRepository<PdfDocument, Long> {

}
