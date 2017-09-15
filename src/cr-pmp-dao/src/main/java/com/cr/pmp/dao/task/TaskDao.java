package com.cr.pmp.dao.task;

import java.util.List;

import com.cr.pmp.model.task.SubTask;
import com.cr.pmp.model.task.Task;
import com.cr.pmp.model.task.TaskBoard;

public interface TaskDao {

	public List<TaskBoard> queryTaskBoardByPid(Integer pid);

	public Integer addTaskBoard(TaskBoard taskBoard);

	public Integer addTask(Task task);

	public Integer updTask(Task task);

	public Integer addSubTask(SubTask subTask);

	public Integer updSubTaskTbid(SubTask subTask);
	
	public Integer delTaskBoard(Integer id);

	public Integer delTask(Integer id);

	public Integer delSubTask(Integer id);

	public Integer delTaskByTBid(Integer tbid);

	public Integer delSubTaskByTBid(Integer tbid);

	public Integer delSubTaskByTid(Integer tid);

	public List<SubTask> querySubTaskByTid(Integer tid);

	public Task queryTaskById(Integer id);

	public List<Task> queryTaskByPid(Integer pid);
}
