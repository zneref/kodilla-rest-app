package com.crud.tasks.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.dto.TrelloBoardDto;
import com.crud.tasks.domain.dto.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTestSuite {
    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void shouldFetchEmptyList() {
        //Given
        List<TrelloListDto> listDtos = Arrays.asList(new TrelloListDto("test_id", "test_name", false));
        List<TrelloBoardDto> boardDtos = Arrays.asList((new TrelloBoardDto("test_id", "test_name", listDtos)));
        List<TrelloList> lists = Arrays.asList(new TrelloList("test_id", "test_name", false));
        List<TrelloBoard> boards = Arrays.asList((new TrelloBoard("test_id", "test_name", lists)));

        when(trelloService.fetchTrelloBoards()).thenReturn(boardDtos);
        when(trelloMapper.mapToBoards(boardDtos)).thenReturn(boards);
        when(trelloMapper.mapToBoardDto(anyList())).thenReturn(new ArrayList<>());
        when(trelloValidator.validateTrelloBoards(boards)).thenReturn(new ArrayList<>());

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(0, trelloBoardDtos.size());
    }

    @Test
    public void shouldFetchTrelloBoards() {
        //Given
        List<TrelloListDto> listDtos = Arrays.asList(new TrelloListDto("test_id", "list_name", false));
        List<TrelloBoardDto> boardDtos = Arrays.asList((new TrelloBoardDto("task_name", "test_id", listDtos)));
        List<TrelloList> lists = Arrays.asList(new TrelloList("test_id", "list_name", false));
        List<TrelloBoard> boards = Arrays.asList((new TrelloBoard("test_id", "task_name", lists)));

        when(trelloService.fetchTrelloBoards()).thenReturn(boardDtos);
        when(trelloMapper.mapToBoards(boardDtos)).thenReturn(boards);
        when(trelloMapper.mapToBoardDto(anyList())).thenReturn(boardDtos);
        when(trelloValidator.validateTrelloBoards(boards)).thenReturn(new ArrayList<>());

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(1, trelloBoardDtos.size());
        trelloBoardDtos.forEach(boardDto -> {
            assertEquals("test_id", boardDto.getId());
            assertEquals("task_name", boardDto.getName());
            boardDto.getLists().forEach(listDto -> {
                assertEquals("test_id", listDto.getId());
                assertEquals("list_name", listDto.getName());
                assertEquals(false, listDto.isClosed());

            });
        });
    }
}