package com.crud.tasks.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BadgesDto {
    @JsonProperty("votes")
    private Long votes;

    @JsonProperty("attachmentsByType")
    private AttachmentsByTypeDto attachmentsByType;
}
