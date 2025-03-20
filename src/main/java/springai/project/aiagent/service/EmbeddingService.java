package springai.project.aiagent.service;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Random;

@Service
public class EmbeddingService {

    public float[] generateEmbedding(String text) {
        float[] embedding = new float[768]; // Longueur en fonction du modèle utilisé
        Random random = new Random();

        for (int i = 0; i < embedding.length; i++) {
            embedding[i] = random.nextFloat(); // Valeur aléatoire entre 0.0 et 1.0
        }

        return embedding;
    }
}
