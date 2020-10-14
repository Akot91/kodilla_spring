package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TrelloFacade trelloFacade;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://localhost:8080/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name" ,adminConfig.getAdminName());
        context.setVariable("preview_message", "Actual tasks list number: " + trelloFacade.fetchTrelloBoards().size());
        context.setVariable("goodbye_message", "Thank you for everything biacz!!");
        context.setVariable("company_details", adminConfig.getCompanyDetails());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
