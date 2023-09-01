package simtodo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import simtodo.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	List<Task> findTaskByStatus(boolean status);

	Task getReferenceById(String id);
	
	void deleteById(String id);
}
