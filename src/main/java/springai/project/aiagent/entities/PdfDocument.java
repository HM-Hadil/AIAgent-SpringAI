package springai.project.aiagent.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "pdf_documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PdfDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Lob
    private byte[] fileData;

    @Column(columnDefinition = "vector(768)")
    private float[] embedding;
}
