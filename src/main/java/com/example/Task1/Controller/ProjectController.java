package com.example.Task1.Controller;

import com.example.Task1.payload.ApiResponse;
import com.example.Task1.payload.ProjectDto;
import com.example.Task1.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task1/")
public class ProjectController
{
    @Autowired
    ProjectService projectService;
    @PostMapping("customer/{customerId}/employee/{employeeId}/project")
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto,
                                                    @PathVariable int customerId,
                                                    @PathVariable int employeeId)
    {
        ProjectDto project = this.projectService.createProject(projectDto,customerId,employeeId);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @PutMapping("project/{projectId}")
    public void updateProject(@RequestBody ProjectDto projectDto,
                                                    @PathVariable int projectId)
    {
        this.projectService.updateProject(projectDto,projectId);
    }

    @DeleteMapping("project/{projectId}")
    public void deleteProject(@PathVariable int projectId)
    {
        this.projectService.deleteProjectById(projectId);
    }

    @GetMapping("projects")
    public ResponseEntity<List<ProjectDto>> getAllProjects()
    {
        return new ResponseEntity<>(this.projectService.getAllProjects(),HttpStatus.OK);
    }

    @GetMapping("project/{projectId}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable int projectId)
    {
        ProjectDto project = this.projectService.getProjectById(projectId);
        return new ResponseEntity<>(project,HttpStatus.OK);
    }

    @GetMapping("employee/{employeeId}/project")
    public ResponseEntity<List<ProjectDto>> getProjectByEmployee(@PathVariable int employeeId)
    {
        return new ResponseEntity<>(this.projectService.getProjectByEmployeeId(employeeId),HttpStatus.OK);
    }

    @GetMapping("customer/{customerId}/project")
    public ResponseEntity<List<ProjectDto>> getProjectByCustomer(@PathVariable int customerId)
    {
        return new ResponseEntity<>(this.projectService.getProjectByCustomerId(customerId),HttpStatus.OK);
    }
}
