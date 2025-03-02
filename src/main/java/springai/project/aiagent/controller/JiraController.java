package springai.project.aiagent.controller;

import org.springframework.web.bind.annotation.*;
import springai.project.aiagent.service.JiraClientService;

@RestController
@RequestMapping("/jira")
public class JiraController {
    private final JiraClientService jiraClientService;

    public JiraController(JiraClientService jiraClientService) {
        this.jiraClientService = jiraClientService;
    }

    @GetMapping("/projects")
    public String getProjects() {
        return jiraClientService.getAllProjects();
    }

    @PostMapping("/create-issue")
    public String createIssue(@RequestParam String projectKey,
                              @RequestParam String summary,
                              @RequestParam String description,
                              @RequestParam String issueType) {
        return jiraClientService.createIssue(projectKey, summary, description, issueType);
    }
}
