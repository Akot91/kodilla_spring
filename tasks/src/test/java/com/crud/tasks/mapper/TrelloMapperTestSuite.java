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

    @Test(expected = NullPointerException.class)
    public void mapToCardDtoshouldReturnNullPointerException(){
        //Given When Then
        TrelloCardDto result = trelloMapper.mapToCardDto(null);
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

    @Test(expected = NullPointerException.class)
    public void mapToCardshouldReturnNullPointerException(){
        //Given When Then
        TrelloCard result = trelloMapper.mapToCard(null);
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
    public void shouldReturnEmptyTrelloListDtoList() {
        //Given
        List<TrelloListDto> testee = new ArrayList<>();

        //When
        List<TrelloList> result = trelloMapper.mapToList(testee);

        //Then
        Assert.assertEquals(0, result.size());
    }

    @Test(expected = NullPointerException.class)
    public void mapToTrelloListShouldReturnNullPointerException(){
        //Given When Then
        List<TrelloList> result = trelloMapper.mapToList(null);
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
    public void shouldReturnEmptyTrelloDtoListList() {
        //Given
        List<TrelloList> testee = new ArrayList<>();

        //When
        List<TrelloListDto> result = trelloMapper.mapToDtoList(testee);

        //Then
        Assert.assertEquals(0, result.size());
    }

    @Test(expected = NullPointerException.class)
    public void mapToTrelloDtoListShouldReturnNullPointerException(){
        //Given When Then
        List<TrelloListDto> result = trelloMapper.mapToDtoList(null);
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
    public void shouldReturnEmptyBoardsDtoList() {
        //Given
        List<TrelloBoard> testee = new ArrayList<>();

        //When
        List<TrelloBoardDto> result = trelloMapper.mapToBoardsDto(testee);

        //Then
        Assert.assertEquals(0, result.size());
    }

    @Test(expected = NullPointerException.class)
    public void mapToBoardsDtoListShouldReturnNullPointerException(){
        //Given When Then
        List<TrelloBoardDto> result = trelloMapper.mapToBoardsDto(null);
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

    @Test
    public void shouldReturnEmptyBoardsList() {
        //Given
        List<TrelloBoardDto> testee = new ArrayList<>();

        //When
        List<TrelloBoard> result = trelloMapper.mapToBoards(testee);

        //Then
        Assert.assertEquals(0, result.size());
    }

    @Test(expected = NullPointerException.class)
    public void mapToBoardsListShouldReturnNullPointerException(){
        //Given When Then
        List<TrelloBoard> result = trelloMapper.mapToBoards(null);
    }

}
