package skillshare.course.SpringDemo.repository;


import skillshare.course.SpringDemo.exceptions.RepositoryException;
import skillshare.course.SpringDemo.model.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class TasksRepository {

  @Autowired
  JdbcTemplate jdbcTemplate;

  public List<Tasks> getAllTasksFromRepository() throws RepositoryException {
    try {
      String sql = "SELECT * FROM MyTasks";
      List<Tasks> tasks;
      tasks = jdbcTemplate.query(sql,
          (resultSet, i) -> {
            UUID taskID = resultSet.getObject("taskID", java.util.UUID.class);
            String description = resultSet.getString("description");
            String owner = resultSet.getString("owner");
            return new Tasks(taskID, owner ,description);
          }


      );
      return tasks;

    } catch (DataAccessException ex) {
      throw new RepositoryException("Fail to query for tasks", ex);
    }
  }

  public Tasks getTaskById(UUID id) throws RepositoryException {

    String sql = "SELECT * FROM MyTasks WHERE taskID = ?";
    try {
      return jdbcTemplate.queryForObject(sql,
          (resultSet, i) -> {

            UUID taskID = resultSet.getObject("taskID", java.util.UUID.class);
            String description = resultSet.getString("description");
            String owner = resultSet.getString("owner");
            return new Tasks(taskID, owner ,description);
          }, id

      );

    } catch (DataAccessException ex) {
      throw new RepositoryException("Fail to query for the task", ex);
    }
  }
  public  UUID createTask(Tasks task, UUID uuid) throws RepositoryException {
    try {
      jdbcTemplate.update(
          "insert into MyTasks (taskID,owner,description) "
              + "values (?, ?, ?)",
          uuid, task.getOwner(), task.getDescrpiption());
      return uuid;
    }
    catch (DataAccessException ex) {
      throw new RepositoryException("failed to insert task", ex);
    }
  }

  public void deleteTask(UUID taskId) throws RepositoryException {
    try {
      jdbcTemplate.update("DELETE FROM MyTasks WHERE taskID=?", taskId);
    } catch (DataAccessException ex) {
      throw new RepositoryException("Fail to delete product with productID:" + taskId);
    }
  }
  public void deleteAllTasks() throws RepositoryException {
    try {
      jdbcTemplate.update("DELETE FROM MyTasks");
    } catch (DataAccessException ex) {
      throw new RepositoryException("Fail to delete all tasks");
    }
  }
  public void updateProduct(Tasks task) throws RepositoryException {

    String sql = "update myTasks set owner=?, description=? "
        + "where taskID=?";
    try {
      jdbcTemplate.update(sql, task.getOwner(), task.getDescrpiption(), task.getTaskId());
    } catch (DataAccessException ex) {
      throw new RepositoryException("Fail to update product", ex);
    }
  }



}






