package com.crud.tasks.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTestSuite {

    @InjectMocks
    private TrelloValidator trelloValidator;

    @Test
    public void shouldReturnValidatedBoards() {
        //Given
        TrelloList trelloList = new TrelloList("id", "name", false);
        List<TrelloList> trelloLists = new ArrayList<>();

        trelloLists.add(trelloList);

        TrelloBoard trelloBoard = new TrelloBoard("test", "Test", trelloLists);
        TrelloBoard trelloBoard2 = new TrelloBoard("trello board", "trello board", trelloLists);

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);
        trelloBoards.add(trelloBoard2);

        //When
        List<TrelloBoard> result = trelloValidator.validateBoards(trelloBoards);

        //Then
        Assert.assertEquals(1, result.size());
    }

    @Test(expected = NullPointerException.class)
    public void shouldReturnException() {
        //Given When Then
        List<TrelloBoard> result = trelloValidator.validateBoards(null);
    }
}
