package com.cr.pmp.service.task.impl;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cr.pmp.common.dict.FilePathDict;
import com.cr.pmp.common.result.Result;
import com.cr.pmp.common.utils.DateUtils;
import com.cr.pmp.common.utils.ExcelUtils;
import com.cr.pmp.common.utils.LogUtils;
import com.cr.pmp.dao.task.TaskDao;
import com.cr.pmp.model.task.SubTask;
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
	public void exportProjectTask(Integer pid, String pname,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Task> tasks = taskDao.queryTaskByPid(pid);
			String tempPath = request.getSession().getServletContext()
					.getRealPath(FilePathDict.PROJECTTASKEXCELTEMPLATE);
			ExcelUtils excelUtils = new ExcelUtils(tempPath);
			HSSFSheet sheet = excelUtils.getWorkbook().getSheet("Sheet1");
			sheet.getRow(0).getCell(1).setCellValue(pname);
			int i = 2;
			for (Task task : tasks) {
				sheet.getRow(i).createCell(1).setCellValue(task.getName());
				sheet.getRow(i).createCell(2).setCellValue(DateUtils.formatDateTime(task.getEndDate()));
				sheet.getRow(i).createCell(4).setCellValue(task.getPerformerName());
				sheet.getRow(i).createCell(5).setCellValue(task.getRemarks());
				i++;
				for(SubTask subTask : task.getSubTasks()){
					sheet.getRow(i).createCell(1).setCellValue("	        "+subTask.getName());
					sheet.getRow(i).createCell(2).setCellValue(DateUtils.formatDateTime(subTask.getEndDate()));
					sheet.getRow(i).createCell(4).setCellValue(subTask.getPerformerName());
					sheet.getRow(i).createCell(5).setCellValue(subTask.getRemarks());
					i++;
				}
			}
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(pname, "UTF-8") + ".xls");
			excelUtils.exportXLS(response.getOutputStream());
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
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
	public Result updTask(Task task) {
		Result result = new Result();
		try {
			int flag = taskDao.updTask(task);
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
	public Result addSubTask(SubTask subTask) {
		Result result = new Result();
		try {
			int flag = taskDao.addSubTask(subTask);
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
	public Result delSubTask(Integer id) {
		Result result = new Result();
		try {
			int flag = taskDao.delSubTask(id);
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
	@Transactional
	public Result delTask(Integer id) {
		Result result = new Result();
		try {
			int flag = taskDao.delTask(id);
			if (flag > 0) {
				taskDao.delSubTaskByTid(id);
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
	@Transactional
	public Result delTaskBoard(Integer id) {
		Result result = new Result();
		try {
			int flag = taskDao.delTaskBoard(id);
			if (flag > 0) {
				taskDao.delSubTaskByTBid(id);
				taskDao.delTaskByTBid(id);
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
	public Result querySubTaskByTid(Integer tid) {
		Result result = new Result();
		try {
			List<SubTask> subTaskList = taskDao.querySubTaskByTid(tid);
			result.addObject("subTaskList", subTaskList);
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Result queryTaskById(Integer id) {
		Result result = new Result();
		try {
			Task task = taskDao.queryTaskById(id);
			result.addObject("task", task);
		} catch (Exception e) {
			LogUtils.error(e.getMessage(), e);
		}
		return result;
	}
}
