package com.crud.tasks.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TrelloCard {

    private String name;
    private String description;
    private String pos;
    private String listId;
}
