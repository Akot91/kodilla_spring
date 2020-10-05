package com.crud.tasks.service;

import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloConfigTestSuite {

    @Autowired
    private TrelloConfig trelloConfig;

    @Test
    public void shouldReturnApiEndpoint() {
        //Given When
        String result = trelloConfig.getTrelloApiEndpoint();

        //Then
        Assert.assertEquals("https://api.trello.com/1", result);
    }

    @Test
    public void shouldReturnAppKey() {
        //Given When
        String result = trelloConfig.getTrelloAppKey();

        //Then
        Assert.assertEquals("e12fc65597b500e73b7eb2be14dda9af", result);
    }

    @Test
    public void shouldReturnToken() {
        //Given When
        String result = trelloConfig.getTrelloToken();

        //Then
        Assert.assertEquals("4d142b047cfe2dd2b84f056776da1cae6fc33662d698cfea99df3d8b78ca1f26", result);
    }

    @Test
    public void shouldReturnUser() {
        //Given When
        String result = trelloConfig.getUser();

        //Then
        Assert.assertEquals("OsamodasxD", result);
    }
}
