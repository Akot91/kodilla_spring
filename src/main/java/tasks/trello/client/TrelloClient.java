package tasks.trello.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import tasks.domain.CreatedTrelloCardDto;
import tasks.domain.TrelloBoardDto;
import tasks.domain.TrelloCardDto;
import tasks.trello.config.TrelloConfig;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {

    public static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TrelloConfig trelloConfig;

    public List<TrelloBoardDto> getTrelloBoards() {
        try {
            return Arrays.asList(Optional.ofNullable(restTemplate.getForObject(getUrl(), TrelloBoardDto[].class)).orElseGet(() -> new TrelloBoardDto[0]));
        } catch (RestClientException e) {
            //LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public CreatedTrelloCardDto createNewCard(TrelloCardDto trelloCardDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getIdList()).build().encode().toUri();
        System.out.println(url);
        return restTemplate.postForObject(url, null, CreatedTrelloCardDto.class);
    }

    private URI getUrl() {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/members/" + trelloConfig.getUser() + "/boards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("fields", "id,name")
                .queryParam("lists", "all").build().encode().toUri();

        return url;
    }

}
