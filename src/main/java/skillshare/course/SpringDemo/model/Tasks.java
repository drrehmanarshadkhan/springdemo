package skillshare.course.SpringDemo.model;

import java.util.Objects;
import java.util.UUID;


public class Tasks {

  private UUID taskId;
  private String descrpiption;
  private String owner;

  public Tasks(UUID taskId, String owner,String descrpiption) {
    this.taskId = taskId;
    this.descrpiption = descrpiption;
    this.owner = owner;
  }

  public UUID getTaskId() {
    return taskId;
  }

  public String getDescrpiption() {
    return descrpiption;
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
    Tasks tasks = (Tasks) o;
    return Objects.equals(taskId, tasks.taskId) &&
        Objects.equals(descrpiption, tasks.descrpiption) &&
        Objects.equals(owner, tasks.owner);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taskId, descrpiption, owner);
  }
}
