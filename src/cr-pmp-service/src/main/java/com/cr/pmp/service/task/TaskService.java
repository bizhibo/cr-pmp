package com.cr.pmp.service.task;

import java.util.Map;

import com.cr.pmp.common.result.Result;
import com.cr.pmp.model.task.Task;
import com.cr.pmp.model.task.TaskBoard;

public interface TaskService {

	public Result queryTaskBoardByPid(Map<String, Object> params);

	public Result addTaskBoard(TaskBoard taskBoard);

	public Result addTask(Task task);

	public Result updateTask(Task task);
}
