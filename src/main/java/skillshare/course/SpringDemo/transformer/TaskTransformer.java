package skillshare.course.SpringDemo.transformer;

import skillshare.course.SpringDemo.event.TasksJson;
import skillshare.course.SpringDemo.model.Tasks;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskTransformer {

  public TasksJson toJson(Tasks tasks) {
    return new TasksJson(tasks.getTaskId(), tasks.getOwner(),tasks.getDescrpiption());
  }

  public List<TasksJson> toJsonList(List<Tasks> modelList) {

    List<TasksJson> jsonList = new ArrayList<>();
    for (int i = 0; i < modelList.size(); i++) {
      jsonList.add(new TasksJson(modelList.get(i).getTaskId(), modelList.get(i).getOwner(), modelList.get(i).getDescrpiption()));

    }
    return jsonList;
  }

  public Tasks fromJson(TasksJson task) {
    return new Tasks(task.getTaskID(), task.getOwner(), task.getTaskDesc());
  }


}
