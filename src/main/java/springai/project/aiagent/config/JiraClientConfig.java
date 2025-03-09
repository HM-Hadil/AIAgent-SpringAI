package springai.project.aiagent.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;

@Configuration
public class JiraClientConfig {

    // Récupération des propriétés depuis application.properties
    @Value("${jira.url}")
    private String jiraUrl;

    @Value("${jira.username}")
    private String username;

    @Value("${jira.password}")
    private String password;

    @Bean
    public WebClient jiraWebClient() {
        // Combiner le username et le password pour l'authentification Basic
        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        // Construction du WebClient avec l'URL de base et les headers par défaut
        return WebClient.builder()
                .baseUrl(jiraUrl + "/rest/api/2")
                .defaultHeader("Authorization", "Basic " + encodedAuth)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}