package simtodo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "task")
public class Task {
  @Id
  private String id;
  private String title;
  private String details;
  private boolean status;
  private String createAt;
  private String updateAt; 
}
