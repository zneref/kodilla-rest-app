package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Arrays;
import java.util.List;


@Service
public class MailCreatorService {
    @Autowired
    AdminConfig adminConfig;
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("preview", "preview");
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost/~manix/tasks_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("bye_message", "Bye!");
        context.setVariable("company", adminConfig.getCompanyName());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("functionality",getFunctionality());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildTasksCardEmail(String message) {
        Context context = new Context();
        context.setVariable("preview", "preview");
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost/~manix/tasks_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("bye_message", "Bye!");
        context.setVariable("company", adminConfig.getCompanyName());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("functionality",getFunctionality());
        return templateEngine.process("mail/tasks-card-mail", context);
    }

    private List<String> getFunctionality() {
        return Arrays.asList("Manage tasks", "Provides connections", "Sending tasks to Trello");
    }

}
