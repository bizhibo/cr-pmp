package com.cr.pmp.service.task;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cr.pmp.common.result.Result;
import com.cr.pmp.model.task.SubTask;
import com.cr.pmp.model.task.Task;
import com.cr.pmp.model.task.TaskBoard;

public interface TaskService {

	public Result queryTaskBoardByPid(Map<String, Object> params);

	public Result addTaskBoard(TaskBoard taskBoard);

	public Result addTask(Task task);

	public Result updTask(Task task);

	public Result addSubTask(SubTask subTask);

	public Result delTaskBoard(Integer id);

	public Result delTask(Integer id);

	public Result delSubTask(Integer id);

	public Result querySubTaskByTid(Map<String, Object> params);

	public Result queryTaskById(Integer id);

	public void exportProjectTask(Integer pid, String pname,
			HttpServletRequest request, HttpServletResponse response);
}
