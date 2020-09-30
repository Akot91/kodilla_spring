package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void shouldReturnTrelloCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("TestName", "TestDesc", "top", "1");

        //When
        TrelloCardDto result = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assert.assertEquals(result.getName(), "TestName");
        Assert.assertEquals(result.getDescription(), "TestDesc");
        Assert.assertEquals(result.getPos(), "top");
        Assert.assertEquals(result.getIdList(), "1");
    }

    @Test
    public void shouldReturnTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("TestDTO", "TestDesc", "top", "1");

        //When
        TrelloCard result = trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assert.assertEquals(result.getName(), "TestDTO");
        Assert.assertEquals(result.getDescription(), "TestDesc");
        Assert.assertEquals(result.getPos(), "top");
        Assert.assertEquals(result.getListId(), "1");
    }

    @Test
    public void shouldReturnTrelloList() {
        //Given
        List<TrelloListDto> testee = new ArrayList<>();
        testee.add(new TrelloListDto("Test name", "Test id", true));
        testee.add(new TrelloListDto("Secound test name", "Test id 2", false));

        //When
        List<TrelloList> result = trelloMapper.mapToList(testee);

        //Then
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void shouldReturnTrelloListDto() {
        //Given
        List<TrelloList> testee = new ArrayList<>();
        testee.add(new TrelloList("Test name", "Test id", true));
        testee.add(new TrelloList("Secound test name", "Test id 2", false));

        //When
        List<TrelloListDto> result = trelloMapper.mapToDtoList(testee);

        //Then
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void shouldReturnBoardsDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();

        List<TrelloBoard> testee = new ArrayList<>();
        testee.add(new TrelloBoard("test id", "test name", trelloLists));
        testee.add(new TrelloBoard("test id 2", "test name", trelloLists));

        //When
        List<TrelloBoardDto> result = trelloMapper.mapToBoardsDto(testee);

        //Then
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void shouldReturnBoards() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();

        List<TrelloBoardDto> testee = new ArrayList<>();
        testee.add(new TrelloBoardDto("test id", "test name", trelloListDtos));
        testee.add(new TrelloBoardDto("test id 2", "test name", trelloListDtos));

        //When
        List<TrelloBoard> result = trelloMapper.mapToBoards(testee);

        //Then
        Assert.assertEquals(2, result.size());
    }

}
