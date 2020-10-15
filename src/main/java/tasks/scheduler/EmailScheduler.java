package tasks.scheduler;

import tasks.config.AdminConfig;
import tasks.domain.Mail;
import tasks.repository.TaskRepository;
import tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    public static final String SUBJECT = "Tasks: One a day email";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "Currently in database you have got: " + size + " " + createPluralWord("task", size),
                "szundipundi@gmail.com"
        ));
    }

    private String createPluralWord(String text, long size) {
        return size == 1 ? text : text + "s";
    }
}
