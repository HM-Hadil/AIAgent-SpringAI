package springai.project.aiagent.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class DatabaseConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public CommandLineRunner initDatabase(JdbcTemplate jdbcTemplate) {
        return args -> {
            // Vérifier si l'extension pgvector est installée
            try {
                jdbcTemplate.execute("CREATE EXTENSION IF NOT EXISTS vector");
                System.out.println("PGVector extension installed or already exists.");
            } catch (Exception e) {
                System.err.println("Failed to create pgvector extension. Make sure it's installed: " + e.getMessage());
            }

            // Créer la table si elle n'existe pas
            jdbcTemplate.execute(
                    "CREATE TABLE IF NOT EXISTS document_chunks (" +
                            "id SERIAL PRIMARY KEY, " +
                            "document_name VARCHAR(255) NOT NULL, " +
                            "chunk_text TEXT NOT NULL, " +
                            "embedding vector(384), " +
                            "chunk_index INTEGER" +
                            ")"
            );
        };
    }
}