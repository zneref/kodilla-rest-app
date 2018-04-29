package com.crud.tasks.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidatorTest {
    @Autowired
    TrelloValidator trelloValidator;

    @Test
    public void validateTrelloBoards() {
        //Given
        List<TrelloBoard> trelloBoards = Arrays.asList(new TrelloBoard("1", "test", new ArrayList<>()));

        //When
        List<TrelloBoard> resultList = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        assertNotNull(resultList);
        assertEquals(0, resultList.size());
    }
}