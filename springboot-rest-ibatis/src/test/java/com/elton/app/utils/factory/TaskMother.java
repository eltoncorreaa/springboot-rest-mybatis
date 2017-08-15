package com.elton.app.utils.factory;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.elton.app.dto.TaskDTO;

/**
 * Classe utilizada nas classes de teste com a finalidade de criacao das
 * entidades reutilizáveis Ver: http://martinfowler.com/bliki/ObjectMother.html
 */
@Component
public class TaskMother {

	private static final String NOME_TASK_TESTE_PADRAO = "Task Padrao";

	public static TaskDTO getTaskDTOPadrao() {
		final TaskDTO taskDTO = new TaskDTO();
		taskDTO.setName(NOME_TASK_TESTE_PADRAO);
		taskDTO.setStartDate(new Date());
		return taskDTO;
	}
}
