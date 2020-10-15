package tasks.trello.facade;

import tasks.domain.TrelloBoard;
import tasks.domain.TrelloCard;
import tasks.mapper.TrelloMapper;
import tasks.service.TrelloService;
import tasks.validator.TrelloValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tasks.domain.CreatedTrelloCardDto;

import tasks.domain.TrelloBoardDto;
import tasks.domain.TrelloCardDto;

import java.util.List;

@Component
public class TrelloFacade {

    private final static Logger LOGGER = LoggerFactory.getLogger(TrelloFacade.class);

    @Autowired
    private TrelloService trelloService;

    @Autowired
    private TrelloMapper trelloMapper;

    @Autowired
    private TrelloValidator trelloValidator;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloService.fetchTrelloBoards());
        List<TrelloBoard> filteredTrelloBoards = trelloValidator.validateBoards(trelloBoards);
        return trelloMapper.mapToBoardsDto(filteredTrelloBoards);
    }

    public CreatedTrelloCardDto createCard(final TrelloCardDto trelloCardDto) {
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        trelloValidator.validateCard(trelloCard);
        return trelloService.createTrelloCard(trelloMapper.mapToCardDto(trelloCard));
    }
}
