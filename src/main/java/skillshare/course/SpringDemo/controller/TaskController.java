package skillshare.course.SpringDemo.controller;

import skillshare.course.SpringDemo.event.TasksJson;
import skillshare.course.SpringDemo.exceptions.ServiceException;
import skillshare.course.SpringDemo.model.Tasks;
import skillshare.course.SpringDemo.service.TaskService;
import skillshare.course.SpringDemo.transformer.TaskTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;


@RestController
public class TaskController {


  private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
  @Autowired
  TaskService taskService;
  @Autowired
  TaskTransformer taskTransformer;

  @RequestMapping(value = "/users", method = RequestMethod.GET)
  public String greet() {
    return "Hello World";
  }

  @RequestMapping(value = "/users/tasks", method = RequestMethod.GET)
  public ResponseEntity<List<TasksJson>> getTasks() {
    try {
      List<Tasks> listOfTasks = taskService.getTasks();
      if (listOfTasks.size() == 0) {
        return new ResponseEntity("no results found", HttpStatus.OK);
      }

      List<TasksJson> taskJson = taskTransformer.toJsonList(listOfTasks);
      return new ResponseEntity(taskJson, HttpStatus.OK);

    } catch (ServiceException e) {
      logger.error("internal server error in getting all listings", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @RequestMapping(value = "/users/tasks/{id}", method = RequestMethod.GET)
  public ResponseEntity<TasksJson> getTaskById(@PathVariable("id") UUID id) {

    try {
      Tasks task = taskService.getTaskById(id);
      TasksJson tasksJson = taskTransformer.toJson(task);
      return new ResponseEntity<>(tasksJson, HttpStatus.OK);

    } catch (ServiceException e) {
      logger.error("bad request in getting task by ID", id, e);
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
  //  @RequestMapping(value = "/users/tasks/hateos/{id}", method = RequestMethod.GET)
//  public org.springframework.hateoas.Resource<TasksJson> getTaskByIdHATEOS(@PathVariable UUID id) throws ServiceException {
//    Tasks task = null;
//    task = taskService.getTaskById(id);
//    if (task == null) {
//      throw new RuntimeException("ID does not exist");
//    }
//    TasksJson tasksJson = taskTransformer.toJson(task);
//    org.springframework.hateoas.Resource<TasksJson> myTasks = new org.springframework.hateoas.Resource<TasksJson>(tasksJson);
//    ControllerLinkBuilder myLink = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).getTasks());
//    ControllerLinkBuilder myLink1 = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).getTaskById(id));
//    myTasks.add(myLink.withRel("parent"));
//    myTasks.add(myLink1.withSelfRel());
//    return myTasks;
//
//  }


  @RequestMapping(value = "/task", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Tasks> createTask(@RequestBody TasksJson task) throws ServiceException {
    UUID uuid = UUID.randomUUID();
    Tasks modelTask = taskTransformer.fromJson(task);
    UUID id = taskService.createTask(modelTask, uuid);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    return ResponseEntity.created(uri).build();
  }

  @RequestMapping(value = "/task/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> deleteTask(@PathVariable("id") UUID id) throws ServiceException {
    taskService.deleteTask(id);
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value = "/tasks", method = RequestMethod.DELETE)
  public ResponseEntity<Void> deleteAllTasks() throws ServiceException {
    taskService.deleteAllTasks();
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }




}
