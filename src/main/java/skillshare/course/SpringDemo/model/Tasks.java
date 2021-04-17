package skillshare.course.SpringDemo.model;

import java.rmi.server.UID;
import java.util.Objects;
import java.util.UUID;

public class Tasks {

    private UUID taskId;
    private String description;
    private String owner;

    public Tasks(UUID taskId, String description, String owner) {
        this.taskId = taskId;
        this.description = description;
        this.owner = owner;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tasks tasks = (Tasks) o;
        return Objects.equals(taskId, tasks.taskId) && Objects.equals(description, tasks.description) && Objects.equals(owner, tasks.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, description, owner);
    }
}
