package com.example.Task1.service;

import com.example.Task1.payload.ProjectDto;

import java.util.List;

public interface ProjectService
{
    ProjectDto createProject(ProjectDto projectDto,int customerId,int employeeId);
    ProjectDto updateProject(ProjectDto projectDto,int projectId);
    ProjectDto getProjectById(int projectId);
    List<ProjectDto> getAllProjects();
    void deleteProjectById(int projectId);

    List<ProjectDto> getProjectByEmployeeId(int employeeId);
    List<ProjectDto> getProjectByCustomerId(int customerId);
}
