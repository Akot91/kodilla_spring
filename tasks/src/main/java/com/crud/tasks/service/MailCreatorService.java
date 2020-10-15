package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://localhost:8080/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name" ,adminConfig.getAdminName());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String tasksStatusEmail(String message) {
        List<String> additionalInfo;

        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();
        additionalInfo = trelloBoardDtos.stream()
                .map(board -> board.getName())
                .collect(Collectors.toList());

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_count", trelloFacade.fetchTrelloBoards().size());
        context.setVariable("admin_config", adminConfig);
        context.setVariable("is_task_list_empty", trelloFacade.fetchTrelloBoards().size() > 0);
        context.setVariable("additional_info", additionalInfo);
        context.setVariable("goodbye_message", "Thank you for using Trello");
        return templateEngine.process("mail/trello-tasks-number-status-mail", context);
    }
}
