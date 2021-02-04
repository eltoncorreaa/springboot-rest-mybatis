package com.elton.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elton.app.dto.TaskDTO;
import com.elton.app.service.TaskService;
import com.elton.app.util.Error;
import com.elton.app.util.TaskExceptionHandler;


@CrossOrigin
@RestController
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping("/api/v1/tasks")
	public ResponseEntity<?> listAll(@RequestParam final Map<String, String> params){
		try {
			return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
		} catch (final RuntimeException e) {
			return new ResponseEntity<Error>(new Error(1, TaskExceptionHandler.getExcetionError(e)), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping("/api/v1/expenses/{id}")
	public ResponseEntity<?> get(@PathVariable final Long id){
		try {
			final TaskDTO dto = taskService.findById(id);
			return new ResponseEntity<TaskDTO>(dto, HttpStatus.OK);
		} catch (final RuntimeException e) {
			return new ResponseEntity<Error>(new Error(1, TaskExceptionHandler.getExcetionError(e)), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PostMapping("/api/v1/expenses")
	public ResponseEntity<?> save(@RequestBody final TaskDTO dto){
		try {
			final TaskDTO inserted= taskService.persist(dto);
			return new ResponseEntity<TaskDTO>(inserted, HttpStatus.OK);
		} catch (final RuntimeException e) {
			return new ResponseEntity<Error>(new Error(1, TaskExceptionHandler.getExcetionError(e)), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PutMapping("/api/v1/expenses")
	public ResponseEntity<?> update(@RequestBody final TaskDTO dto){
		try {
			final TaskDTO updated= taskService.merge(dto);
			return new ResponseEntity<TaskDTO>(updated, HttpStatus.OK);
		} catch (final RuntimeException e) {
			return new ResponseEntity<Error>(new Error(1, TaskExceptionHandler.getExcetionError(e)), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@DeleteMapping("/api/v1/expenses/{id}")
	public void delete(@PathVariable final Long id){
		taskService.delete(id);
	}
}
