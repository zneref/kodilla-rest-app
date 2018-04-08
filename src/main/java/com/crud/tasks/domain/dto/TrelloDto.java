package com.crud.tasks.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrelloDto {
    @JsonProperty("board")
    private Long board;

    @JsonProperty("card")
    private Long card;
}
