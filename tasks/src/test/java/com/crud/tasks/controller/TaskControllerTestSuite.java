package com.crud.tasks.controller;

import com.crud.tasks.EntityException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.NestedCheckedException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldGetTasks() throws Exception {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "title", "content"));

        List<TaskDto> dtoTasks = new ArrayList<>();
        dtoTasks.add(new TaskDto(1L, "title", "content"));

        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskListDto(tasks)).thenReturn(dtoTasks);

        //When Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("title")))
                .andExpect(jsonPath("$[0].content", is("content")));
    }

    @Test
    public void shouldGetTask() throws Exception {
        //Given
        Task task = new Task(1L, "title", "content");
        TaskDto taskDto = new TaskDto(1L, "title", "content");

        when(dbService.getTask(1L)).thenReturn(java.util.Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("title")))
                .andExpect(jsonPath("$.content", is("content")));
    }

    @Test
    public void shouldGetTaskThrowException() throws Exception {
        //Given
        doThrow(new TaskNotFoundException()).when(dbService).getTask(100L);

        //When Then
        assertThatThrownBy(() ->
            mockMvc.perform(get("/v1/task/getTask?taskId=100").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(500))
                    .andExpect(content().string("Task 100 not found!")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        Task task = new Task(1L, "title", "content");

        when(dbService.saveTask(ArgumentMatchers.any())).thenReturn(taskMapper.mapToTask(taskDto));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        //When Then
        mockMvc.perform(post("/v1/task/createTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("title")))
                .andExpect(jsonPath("$.content", is("content")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given When Then
        mockMvc.perform(delete("/v1/task/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("taskId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());

        verify(dbService, times(1)).deleteTask(1L);
    }

    @Test
    public void shouldDeleteTaskThrowException() throws Exception {
        // Given
        doThrow(new EntityNotFoundException("Task 100 not found!")).when(dbService).deleteTask(100L);

        // When & Then
        mockMvc.perform(delete("/v1/task/100")
                .contentType(MediaType.APPLICATION_JSON)
                .param("taskId", "100"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Task 100 not found!"));
    }
}
