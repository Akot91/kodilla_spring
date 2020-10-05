package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {

    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void shouldMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(10L, "Test name", "Test content");

        //When
        Task result = taskMapper.mapToTask(taskDto);

        //Then
        Assert.assertEquals(new Long(10L), taskDto.getId());
        Assert.assertEquals("Test name", result.getTitle());
        Assert.assertEquals("Test content", result.getContent());
    }

    @Test(expected = NullPointerException.class)
    public void mapToTaskShouldReturnException() {
        //Given When then
        Task result = taskMapper.mapToTask(null);
    }

    @Test
    public void shouldMapToTaskDto() {
        //Given
        Task task = new Task(5L, "Test", "Test content");

        //When
        TaskDto result = taskMapper.mapToTaskDto(task);

        //Then
        Assert.assertEquals(new Long(5L), result.getId());
        Assert.assertEquals("Test", result.getTitle());
        Assert.assertEquals("Test content", result.getContent());
    }

    @Test(expected = NullPointerException.class)
    public void mapToTaskDtoShouldReturnException() {
        //Given When then
        TaskDto result = taskMapper.mapToTaskDto(null);
    }

    @Test
    public void shouldReturnTaskListDto() {
        //Given
        List<Task> tasks = new ArrayList<>();
        Task task1 = new Task(5L, "Test", "Test content");
        Task task2 = new Task(10L, "Test 2", "Test content 2");
        tasks.add(task1);
        tasks.add(task2);

        //When
        List<TaskDto> result = taskMapper.mapToTaskListDto(tasks);

        //Then
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void shouldReturnEmptyList() {
        //Given
        List<Task> tasks = new ArrayList<>();

        //When
        List<TaskDto> result = taskMapper.mapToTaskListDto(tasks);

        //Then
        Assert.assertEquals(0, result.size());
    }

    @Test(expected = NullPointerException.class)
    public void mapToTaskListDtoShouldReturnException() {
        //Given When then
        List<TaskDto> result = taskMapper.mapToTaskListDto(null);
    }
}
