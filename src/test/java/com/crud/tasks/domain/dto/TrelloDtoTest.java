package com.crud.tasks.domain.dto;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrelloDtoTest {

    @Test
    public void getBoard() {
        //Given
        TrelloDto dto = new TrelloDto(123L,456L);
        //When
        long board = dto.getBoard();
        //Then
        assertEquals(123L, board);
    }

    @Test
    public void getCard() {
        //Given
        TrelloDto dto = new TrelloDto(123L,456L);
        //When
        long card = dto.getCard();
        //Then
        assertEquals(456L, card);
    }
}