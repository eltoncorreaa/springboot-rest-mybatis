package com.elton.app.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.elton.app.dto.TaskDTO;
import com.elton.app.model.Task;

public class TaskConverter {

	public static Task toModel(final TaskDTO dto){
		final ModelMapper mapper = new ModelMapper();
		return mapper.map(dto, Task.class);
	}

	public static TaskDTO toDTO(final Task deal){
		final ModelMapper mapper = new ModelMapper();
		return mapper.map(deal, TaskDTO.class);
	}

	public static List<TaskDTO> toListDTO(final List<Task> list){
		final List<TaskDTO> results = new ArrayList<>();
		for (final Task task : list){
			results.add(toDTO(task));
		}
		return results;
	}
}
