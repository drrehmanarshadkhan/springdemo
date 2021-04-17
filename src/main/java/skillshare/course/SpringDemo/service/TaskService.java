package skillshare.course.SpringDemo.service;

import skillshare.course.SpringDemo.exception.RepositoryException;
import skillshare.course.SpringDemo.exception.ServiceException;
import skillshare.course.SpringDemo.model.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skillshare.course.SpringDemo.repository.TaskRepository;


import java.util.List;
import java.util.UUID;

@Service
public class TaskService {


    @Autowired
    TaskRepository tasksRepository;

    public List<Tasks> getTasks() throws ServiceException {
        try {
            return tasksRepository.getAllTasksFromRepository();
        } catch (RepositoryException e) {
            throw new ServiceException("cannot get tasks", e);
        }
    }

    public Tasks getTaskById(UUID id) throws ServiceException {
        try {
            return tasksRepository.getTaskById(id);
        } catch (RepositoryException e) {
            throw new ServiceException("cannot retreive task by ID", e);
        }

    }
    public UUID createTask(Tasks task, UUID uuid) throws ServiceException {
        try {
            return tasksRepository.createTask(task,uuid);
        } catch (RepositoryException e) {
            throw new ServiceException("Cannot create task ", e);
        }
    }
    public void deleteTask(UUID taskId) throws ServiceException {
        try {
            tasksRepository.deleteTask(taskId);
        } catch (RepositoryException e) {
            throw new ServiceException("Cannot delete task with ID "+taskId.toString(), e);
        }
    }
    public void deleteAllTasks() throws ServiceException {
        try {
            tasksRepository.deleteAllTasks();
        } catch (RepositoryException e) {
            throw new ServiceException("Cannot delete all tasks", e);
        }
    }



}