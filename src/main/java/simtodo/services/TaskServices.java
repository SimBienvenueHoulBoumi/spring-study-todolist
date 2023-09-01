package simtodo.services;

import java.util.List;

import simtodo.dto.TaskDto;
import simtodo.models.Task;

public interface TaskServices {
   void create(TaskDto task);
   List<Task> all();
   Task one(String id);
   List<Task> findByStatus(boolean status);
   void delete(String id);
   Task update(String id, TaskDto task);
}
