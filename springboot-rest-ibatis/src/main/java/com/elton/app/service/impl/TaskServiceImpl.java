package com.elton.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elton.app.converter.TaskConverter;
import com.elton.app.dto.TaskDTO;
import com.elton.app.exception.MultipleTaskException;
import com.elton.app.exception.TaskException;
import com.elton.app.mapper.TaskMapper;
import com.elton.app.model.Task;
import com.elton.app.service.TaskService;

@Transactional(readOnly = true)
@Service
public class TaskServiceImpl implements TaskService{

	@Autowired
	private TaskMapper taskMapper;

	private static final String NOME_OBRIGATORIO = "O Nome é obrigatório.";
	private static final String lOCK_OPTIMISTIC = "Entidade desatualizada, favor atualizar a página para concluir alteração.";

	@Override
	public List<TaskDTO> buscarPorFiltro(final TaskDTO company) {
		final TaskDTO entidade= new TaskDTO();

		final List<TaskDTO> lista= new ArrayList<TaskDTO>();
		lista.add(entidade);
		return lista;
	}

	@Override
	public TaskDTO findById(final Long id) {
		return TaskConverter.toDTO(taskMapper.findById(id));
	}

	@Transactional(readOnly = false)
	@Override
	public TaskDTO persist(final TaskDTO taskDTO) {
		checkException(validatePersistTask(taskDTO));
		final Long idInserted= taskMapper.insert(TaskConverter.toModel(taskDTO));
		return TaskConverter.toDTO(taskMapper.findById(idInserted));
	}

	@Transactional(readOnly = false)
	@Override
	public TaskDTO merge(final TaskDTO task) {
		final Task taskModel= TaskConverter.toModel(task);
		taskModel.setLastUpdateTime(new Date());
		checkException(validateUpdateTask(task));
		final Long idUpdated= taskMapper.update(TaskConverter.toModel(task));
		return TaskConverter.toDTO(taskMapper.findById(idUpdated));
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(final Long id) {
		taskMapper.delete(id);
	}

	@Override
	public List<TaskDTO> findAll() {
		return TaskConverter.toListDTO(taskMapper.findAll());
	}

	private void checkException(final ArrayList<TaskException> exceptions) {
		if (!exceptions.isEmpty()) {
			throw new MultipleTaskException(exceptions);
		}
	}

	private ArrayList<TaskException> validatePersistTask(final TaskDTO taskDTO) {
		final ArrayList<TaskException> errors = new ArrayList<TaskException>();
		validateName(taskDTO, errors);
		return errors;
	}

	private ArrayList<TaskException> validateUpdateTask(final TaskDTO taskDTO) {
		final ArrayList<TaskException> errors = new ArrayList<TaskException>();
		validateName(taskDTO, errors);
		validateLockOptimistic(taskDTO, errors);
		return errors;
	}

	private void validateName(final TaskDTO taskDTO, final ArrayList<TaskException> errors) {
		if (StringUtils.isBlank(taskDTO.getName())) {
			errors.add(new TaskException(NOME_OBRIGATORIO));
		}
	}

	private void validateLockOptimistic(final TaskDTO task, final ArrayList<TaskException> errors) {
		if (!taskMapper.findById(task.getId()).getVersion().equals(task.getVersion()) ) {
			errors.add(new TaskException(lOCK_OPTIMISTIC));
		}
	}
}
