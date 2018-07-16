package com.elton.app.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.elton.app.converter.TaskConverter;
import com.elton.app.dto.TaskDTO;
import com.elton.app.mapper.TaskMapper;
import com.elton.app.model.Task;
import com.elton.app.service.TaskServiceImpl;
import com.elton.app.utils.factory.TaskMother;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplUnitTest {

	@InjectMocks
	private TaskServiceImpl taskServiceImpl;

	@Mock
	private TaskMapper taskMapper;

	@Test
	public void persistTest() {
		final TaskDTO taskDto = TaskMother.getTaskDTOPadrao();
		final Task task = TaskMother.getTaskPadrao();
		Mockito.when(taskMapper.insert(TaskConverter.toModel(taskDto))).thenReturn(1L);

		Mockito.when(taskMapper.findById(1L)).thenReturn(task);
		final TaskDTO retorno = taskServiceImpl.persist(taskDto);
		Assert.assertEquals(TaskConverter.toModel(retorno), task);
	}

}
