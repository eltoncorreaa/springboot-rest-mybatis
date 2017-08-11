package com.elton.app.service;

import java.util.List;

import com.elton.app.dto.TaskDTO;


public interface TaskService {

	/**
	 * Persiste uma instancia da entidade Empresa.
	 *
	 * @param task
	 *        Instancia de Acao a ser persistida
	 * @return entidade persistida
	 */
	TaskDTO persist(TaskDTO task);

	TaskDTO findById(Long id);

	TaskDTO merge(TaskDTO task);

	void delete(Long id);

	List<TaskDTO> findAll();

	List<TaskDTO> buscarPorFiltro(TaskDTO taskDto);
}
