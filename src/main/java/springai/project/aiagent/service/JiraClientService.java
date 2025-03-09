package springai.project.aiagent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class JiraClientService {
    private final WebClient webClient;

    // Injection du bean jiraWebClient défini dans la configuration
    @Autowired
    public JiraClientService(WebClient jiraWebClient) {
        this.webClient = jiraWebClient;
    }

    // ✅ Récupérer la liste des projets Jira
    public String getAllProjects() {
        return webClient.get()
                .uri("/project")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    // ✅ Créer un ticket JIRA
    public String createIssue(String projectKey, String summary, String description, String issueType) {
        String body = String.format("""
                {
                    "fields": {
                        "project": { "key": "%s" },
                        "summary": "%s",
                        "description": "%s",
                        "issuetype": { "name": "%s" }
                    }
                }
                """, projectKey, summary, description, issueType);

        return webClient.post()
                .uri("/issue")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
