package com.cr.pmp.controller.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cr.pmp.common.base.BaseController;
import com.cr.pmp.common.result.Result;
import com.cr.pmp.model.project.Project;
import com.cr.pmp.model.task.Task;
import com.cr.pmp.model.task.TaskBoard;
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
		Result result = projectService.addProject((Project) this
				.getParamsObject(Project.class));
		return result.toJson();
	}

	@RequestMapping("/project-board")
	public Result projectBoard() {
		Result result = userService.queryAllUser();
		result.addObject("pid", this.getParams("pid"));
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
		Result result = taskService.updateTask((Task) this
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
}
