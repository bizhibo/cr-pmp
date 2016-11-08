package com.cr.pmp.service.task.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cr.pmp.common.result.Result;
import com.cr.pmp.common.utils.LogUtils;
import com.cr.pmp.dao.task.TaskDao;
import com.cr.pmp.model.task.Task;
import com.cr.pmp.model.task.TaskBoard;
import com.cr.pmp.service.task.TaskService;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDao taskDao;

	@Override
	public Result queryTaskBoardByPid(Map<String, Object> params) {
		Result result = new Result();
		try {
			List<TaskBoard> list = taskDao.queryTaskBoardByPid(Integer
					.valueOf(params.get("pid").toString()));
			result.addObject("boards", list);
			result.addObject("pid", params.get("pid"));
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Result addTaskBoard(TaskBoard taskBoard) {
		Result result = new Result();
		try {
			int flag = taskDao.addTaskBoard(taskBoard);
			if (flag > 0) {
				result.setResultCode(true);
			} else {
				result.setResultCode(false);
			}
		} catch (Exception e) {
			result.setResultCode(false);
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Result addTask(Task task) {
		Result result = new Result();
		try {
			int flag = taskDao.addTask(task);
			if (flag > 0) {
				result.setResultCode(true);
			} else {
				result.setResultCode(false);
			}
		} catch (Exception e) {
			result.setResultCode(false);
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Result updateTask(Task task) {
		Result result = new Result();
		try {
			int flag = taskDao.updateTask(task);
			if (flag > 0) {
				result.setResultCode(true);
			} else {
				result.setResultCode(false);
			}
		} catch (Exception e) {
			result.setResultCode(false);
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

}
