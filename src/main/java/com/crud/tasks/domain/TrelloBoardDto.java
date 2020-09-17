package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloBoardDto {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "lists")
    private List<TrelloListDto> lists;
}
