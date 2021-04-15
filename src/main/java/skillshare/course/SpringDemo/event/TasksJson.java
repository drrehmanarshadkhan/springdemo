package skillshare.course.SpringDemo.event;

import java.util.Objects;
import java.util.UUID;

public class TasksJson {

  private UUID taskID;
  private String taskDesc;
  private String owner;

  public TasksJson(UUID taskID, String owner, String taskDesc) {
    this.taskID = taskID;
    this.taskDesc = taskDesc;
    this.owner = owner;
  }
  public TasksJson(){}

  public UUID getTaskID() {
    return taskID;
  }

  public String getTaskDesc() {
    return taskDesc;
  }

  public String getOwner() {
    return owner;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TasksJson tasksJson = (TasksJson) o;
    return Objects.equals(taskID, tasksJson.taskID) &&
        Objects.equals(taskDesc, tasksJson.taskDesc) &&
        Objects.equals(owner, tasksJson.owner);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taskID, taskDesc, owner);
  }
}
