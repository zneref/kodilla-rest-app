package com.crud.tasks.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BadgesDto {
    private Long votes;

    private AttachmentsByTypeDto attachmentsByType;
}
