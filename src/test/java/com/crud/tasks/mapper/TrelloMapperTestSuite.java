package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.dto.TrelloBoardDto;
import com.crud.tasks.domain.dto.TrelloCardDto;
import com.crud.tasks.domain.dto.TrelloListDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {
    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void mapToBoards() {
        //Given
        List<TrelloListDto> trelloListDtos = Arrays.asList(new TrelloListDto("test_id", "test_name", false));
        List<TrelloBoardDto> trelloBoardDtos = Arrays.asList(new TrelloBoardDto("test_id", "test_name", trelloListDtos));

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);

        //Then
        assertEquals(1, trelloBoards.size());
        trelloBoards.forEach(trelloBoard -> {
            assertEquals("test_id", trelloBoard.getId());
            assertEquals("test_name", trelloBoard.getName());
            trelloBoard.getLists().forEach(list -> {
                assertEquals(false, list.isClosed());
                assertEquals("test_id", list.getId());
                assertEquals("test_name", list.getName());
            });
        });
    }

    @Test
    public void mapToList() {
        //Given
        List<TrelloListDto> trelloListDtos = Arrays.asList(new TrelloListDto("test_id", "test_name", false));

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);

        //Then
        assertEquals(1, trelloLists.size());
        trelloLists.forEach(trelloList -> {
            assertEquals("test_id", trelloList.getId());
            assertEquals("test_name", trelloList.getName());
            assertEquals(false, trelloList.isClosed());
        });
    }

    @Test
    public void mapToListDto() {
        //Given
        List<TrelloList> trelloList = Arrays.asList(new TrelloList("test_id", "test_name", false));

        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloList);

        //Then
        assertEquals(1, trelloListDtos.size());
        trelloListDtos.forEach(trelloListDto -> {
            assertEquals("test_id", trelloListDto.getId());
            assertEquals("test_name", trelloListDto.getName());
            assertEquals(false, trelloListDto.isClosed());
        });
    }

    @Test
    public void mapToBoardDto() {
        //Given
        List<TrelloList> trelloLists = Arrays.asList(new TrelloList("test_id", "test_name", false));
        List<TrelloBoard> trelloBoards = Arrays.asList(new TrelloBoard("test_id", "test_name", trelloLists));

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardDto(trelloBoards);

        //Then
        assertEquals(1, trelloBoardDtos.size());
        trelloBoardDtos.forEach(trelloBoardDto -> {
            assertEquals("test_id", trelloBoardDto.getId());
            assertEquals("test_name", trelloBoardDto.getName());
            trelloBoardDto.getLists().forEach(list -> {
                assertEquals("test_id", list.getId());
                assertEquals("test_name", list.getName());
                assertEquals(false, list.isClosed());
            });
        });
    }

    @Test
    public void mapToCardDto() {
        //Given
        TrelloCard card = new TrelloCard("test_name", "test_description", "test_pos", "test_listId");

        //When
        TrelloCardDto cardDto = trelloMapper.mapToCardDto(card);

        //Then
        assertNotNull(cardDto);
        assertEquals("test_name", cardDto.getName());
        assertEquals("test_description", cardDto.getDescription());
        assertEquals("test_pos", cardDto.getPos());
        assertEquals("test_listId", cardDto.getListId());
    }

    @Test
    public void mapToCard() {
        //Given
        TrelloCardDto cardDto = new TrelloCardDto("test_name", "test_description", "test_pos", "test_listId");

        //When
        TrelloCard card = trelloMapper.mapToCard(cardDto);

        //Then
        assertNotNull(card);
        assertEquals("test_name", card.getName());
        assertEquals("test_description", card.getDescription());
        assertEquals("test_pos", card.getPos());
        assertEquals("test_listId", card.getListId());
    }
}