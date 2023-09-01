package simtodo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import simtodo.dto.TaskDto;
import simtodo.models.Task;
import simtodo.serviceImpl.TaskServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v0")
public class TaskControllers {
	
	private TaskServiceImpl taskServiceImpl;
	
	public TaskControllers (TaskServiceImpl taskServiceImpl) {
		this.taskServiceImpl = taskServiceImpl;
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody TaskDto taskDto) {
		this.taskServiceImpl.create(taskDto);
		return new ResponseEntity<>("Task created successfully", HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Task>> all(){
		List<Task> tasks = this.taskServiceImpl.all();
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}
	
	@GetMapping("/one/{id}")
	public ResponseEntity<Task> one(@PathVariable String id ) {
		Task task = this.taskServiceImpl.one(id);
		if (task != null) {
			return new ResponseEntity<>(task, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/findByStatus/{status}")
	public ResponseEntity<List<Task>> status(@PathVariable boolean status){
		List<Task> tasks = this.taskServiceImpl.findByStatus(status);
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}
	
	@PatchMapping("/update/{id}")
	public ResponseEntity<Task> update(@PathVariable String id,@RequestBody TaskDto task) {
		Task updatedTask = this.taskServiceImpl.update(id, task);
		if (updatedTask != null) {
			return new ResponseEntity<>(updatedTask, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable String id) {
		this.taskServiceImpl.delete(id);
	}
}