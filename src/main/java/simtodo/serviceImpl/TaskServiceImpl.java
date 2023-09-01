package simtodo.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import simtodo.dto.TaskDto;
import simtodo.models.Task;
import simtodo.repositories.TaskRepository;
import simtodo.services.TaskServices;

@Service
public class TaskServiceImpl implements TaskServices{
	
	private TaskRepository repository;
	
	@Autowired
	public TaskServiceImpl(TaskRepository taskRepository) {
		this.repository = taskRepository;
	}

	@Override
	public void create(TaskDto task) {
		String id = UUID.randomUUID().toString();
		Task newTask = new Task();
		
		newTask.setId(id);
		newTask.setTitle(task.getTitle());
		newTask.setDetails(task.getDetails());
		newTask.setStatus(false);
		
		String dateTime = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a")
                .format(LocalDateTime.now());
		
		newTask.setCreateAt(dateTime);
		newTask.setUpdateAt(dateTime);
		
		this.repository.save(newTask);
	}

	@Override
	public List<Task> all() {
		return this.repository.findAll();
	}

	@Override
	public Task one(String id) {
		return this.repository.getReferenceById(id);
	}

	@Override
	public List<Task> findByStatus(boolean status) {
		return this.repository.findTaskByStatus(status);
	}

	@Override
	public void delete(String id) {
		this.repository.deleteById(id);
	}

	@Override
	public Task update(String id, TaskDto task) {
	    Task existingTask = this.repository.getReferenceById(id);

	    if (existingTask != null) {
	        if (task.getTitle() != null && !task.getTitle().isEmpty()) {
	            existingTask.setTitle(task.getTitle());
	        }

	        if (task.getDetails() != null && !task.getDetails().isEmpty()) {
	            existingTask.setDetails(task.getDetails());
	        }
	        
	        existingTask.setStatus(task.isStatus());
	        
	        String dateTime = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a")
	                .format(LocalDateTime.now());
	        
	        existingTask.setUpdateAt(dateTime);

	        this.repository.save(existingTask);

	        return existingTask;
	    } else {
	        return null;
	    }
	}



}
