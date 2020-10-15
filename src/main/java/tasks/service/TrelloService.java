package tasks.service;

import tasks.config.AdminConfig;
import tasks.domain.CreatedTrelloCardDto;
import tasks.domain.Mail;
import tasks.domain.TrelloBoardDto;
import tasks.domain.TrelloCardDto;
import tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrelloService {

    public static final String SUBJECT = "Tasks: New Trello Card";

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TrelloClient trelloClient;

    @Autowired
    private SimpleEmailService emailService;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
        /*Optional.ofNullable(newCard).ifPresent(card ->
        emailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "New card: " + newCard.getName() + "has been created in your Trello account",
                ""
        )));*/
        return newCard;
    }
}
