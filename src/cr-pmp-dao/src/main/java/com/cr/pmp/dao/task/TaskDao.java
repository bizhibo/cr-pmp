package com.cr.pmp.dao.task;

import java.util.List;

import com.cr.pmp.model.task.Task;
import com.cr.pmp.model.task.TaskBoard;

public interface TaskDao {

	public List<TaskBoard> queryTaskBoardByPid(Integer pid);

	public Integer addTaskBoard(TaskBoard taskBoard);

	public Integer addTask(Task task);

	public Integer updateTask(Task task);
}
