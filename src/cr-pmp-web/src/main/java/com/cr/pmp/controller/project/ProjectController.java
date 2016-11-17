package com.cr.pmp.controller.project;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cr.pmp.common.base.BaseController;
import com.cr.pmp.common.result.Result;
import com.cr.pmp.model.project.Project;
import com.cr.pmp.model.project.ProjectLeaguer;
import com.cr.pmp.model.task.SubTask;
import com.cr.pmp.model.task.Task;
import com.cr.pmp.model.task.TaskBoard;
import com.cr.pmp.model.user.User;
import com.cr.pmp.service.project.ProjectService;
import com.cr.pmp.service.task.TaskService;
import com.cr.pmp.service.user.UserService;

@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private UserService userService;

	@Autowired
	private TaskService taskService;

	@RequestMapping("/index")
	public Result index() {
		return new Result("/project/index");
	}

	@RequestMapping("/page-list")
	public Result queryProjectList() {
		Result result = projectService.queryProjectPageList(this.getParams());
		result.setViewName("/project/projectList");
		return result;
	}

	@RequestMapping("/add-project")
	@ResponseBody
	public String addProject() {
		User user = (User) this.getLoginUserInfo();
		Result result = projectService.addProject(
				(Project) this.getParamsObject(Project.class),
				user.getUserName());
		return result.toJson();
	}

	@RequestMapping("/project-board")
	public Result projectBoard() {
		Result result = projectService.queryProjectLeguer(Integer.valueOf(this
				.getParams("pid").toString()));
		result.setViewName("/project/board");
		return result;
	}

	@RequestMapping("/board-list")
	@ResponseBody
	public Result queryBoardList() {
		Result result = taskService.queryTaskBoardByPid(this.getParams());
		result.setViewName("/project/boardList");
		return result;
	}

	@RequestMapping("/add-board")
	@ResponseBody
	public String addBoard() {
		Result result = taskService.addTaskBoard((TaskBoard) this
				.getParamsObject(TaskBoard.class));
		return result.toJson();
	}

	@RequestMapping("/update-tbid")
	@ResponseBody
	public String updateTBId() {
		Result result = taskService.updTask((Task) this
				.getParamsObject(Task.class));
		return result.toJson();
	}

	@RequestMapping("/add-task")
	@ResponseBody
	public String addTask() {
		Result result = taskService.addTask((Task) this
				.getParamsObject(Task.class));
		return result.toJson();
	}

	@RequestMapping("/add-sub-task")
	@ResponseBody
	public String addSubTask() {
		Result result = taskService.addSubTask((SubTask) this
				.getParamsObject(SubTask.class));
		return result.toJson();
	}

	@RequestMapping("/del-board")
	@ResponseBody
	public String delTaskBoard() {
		Result result = taskService.delTaskBoard(Integer.valueOf(this
				.getParams("id").toString()));
		return result.toJson();
	}

	@RequestMapping("/del-task")
	@ResponseBody
	public String delTask() {
		Result result = taskService.delTask(Integer.valueOf(this
				.getParams("id").toString()));
		return result.toJson();
	}

	@RequestMapping("/del-sub-task")
	@ResponseBody
	public String delSubTask() {
		Result result = taskService.delSubTask(Integer.valueOf(this.getParams(
				"id").toString()));
		return result.toJson();
	}

	@RequestMapping("/sub-task-index")
	public Result subTaskIndex() {
		Result result = projectService.queryProjectLeguer(Integer.valueOf(this
				.getParams("pid").toString()));
		result.addAllObjects(this.getParams());
		result.setViewName("/project/subTask");
		return result;
	}

	@RequestMapping("/sub-task-list")
	@ResponseBody
	public Result subTaskList() {
		Result result = taskService.querySubTaskByTid(Integer.valueOf(this
				.getParams("tid").toString()));
		result.setViewName("/project/subTaskList");
		return result;
	}

	@RequestMapping("/project-leaguer-index")
	public Result projectLeaguerIndex() {
		Result result = userService.queryAllUser();
		result.addAllObjects(this.getParams());
		result.setViewName("/project/projectLeaguer");
		return result;
	}

	@RequestMapping("/project-leaguer-list")
	@ResponseBody
	public Result projectLeaguerList() {
		Result result = projectService.queryProjectLeguer(Integer.valueOf(this
				.getParams("pid").toString()));
		result.setViewName("/project/projectLeaguerList");
		return result;
	}

	@RequestMapping("/add-project-leaguer")
	@ResponseBody
	public String addProjectLeaguer() {
		Result result = projectService.addProjectLeguer((ProjectLeaguer) this
				.getParamsObject(ProjectLeaguer.class));
		return result.toJson();
	}

	@RequestMapping("/del-project-leaguer")
	@ResponseBody
	public String delProjectLeaguer() {
		Result result = projectService.delProjectLeguer(Integer.valueOf(this
				.getParams("id").toString()));
		return result.toJson();
	}

	@RequestMapping("/export-project-task")
	public void exportProjectTask(HttpServletResponse response) {
		taskService.exportProjectTask(Integer.valueOf(this.getParams("pid")
				.toString()), this.getParams("pname").toString(), request,
				response);
	}
}
