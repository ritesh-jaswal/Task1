package com.example.Task1.seviceimpl;

import com.example.Task1.exceptions.ResourceNotFoundException;
import com.example.Task1.model.Customers;
import com.example.Task1.model.Employees;
import com.example.Task1.model.Projects;
import com.example.Task1.payload.ProjectDto;
import com.example.Task1.repository.CustomerRepo;
import com.example.Task1.repository.EmployeeRepo;
import com.example.Task1.repository.ProjectRepo;
import com.example.Task1.service.ProjectService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService
{
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProjectRepo projectRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Override
    public ProjectDto createProject(ProjectDto projectDto,int customerId,int employeeId)
    {
        Customers customer = this.customerRepo.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Project","Customer Id",customerId));
        Employees employee = this.employeeRepo.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Project","Employee Id",employeeId));

        Projects project = this.modelMapper.map(projectDto,Projects.class);
        project.setStartDate(new Date());
        project.setCustomers(customer);
        project.setEmployees(employee);

        Projects create = this.projectRepo.save(project);
        return this.modelMapper.map(create,ProjectDto.class);
    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDto, int projectId)
    {
        Projects project = this.projectRepo.findById(projectId).orElseThrow(()->new ResourceNotFoundException("Project","Project Id",projectId));
        project.setProjectName(projectDto.getProjectName());
        project.setEndDate(projectDto.getEndDate());

        Projects update = this.projectRepo.save(project);
        return this.modelMapper.map(update,ProjectDto.class);
    }

    @Override
    public ProjectDto getProjectById(int projectId)
    {
        Projects project = this.projectRepo.findById(projectId).orElseThrow(()->new ResourceNotFoundException("Project","Project Id",projectId));

        return this.modelMapper.map(project,ProjectDto.class);
    }

    @Override
    public List<ProjectDto> getAllProjects()
    {
        List<Projects> projects = this.projectRepo.findAll();
        List<ProjectDto> projectDto = projects.stream().map(project -> this.modelMapper.map(project,ProjectDto.class)).collect(Collectors.toList());
        return projectDto;
    }

    @Override
    public void deleteProjectById(int projectId)
    {
        Projects project = this.projectRepo.findById(projectId).orElseThrow(()->new ResourceNotFoundException("Project","Project Id",projectId));
        this.projectRepo.delete(project);
    }

    @Override
    public List<ProjectDto> getProjectByEmployeeId(int employeeId)
    {
        Employees employee = this.employeeRepo.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Project","Employee Id",employeeId));
        List<Projects> projects = this.projectRepo.findAllByEmployees(employee);

        List<ProjectDto> projectDto = projects.stream().map(project -> this.modelMapper.map(project,ProjectDto.class)).collect(Collectors.toList());
        return projectDto;
    }

    @Override
    public List<ProjectDto> getProjectByCustomerId(int customerId)
    {
        Customers customer = this.customerRepo.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Project","Customer Id",customerId));
        List<Projects> projects = this.projectRepo.findAllByCustomers(customer);

        List<ProjectDto> projectDto = projects.stream().map(project -> this.modelMapper.map(project,ProjectDto.class)).collect(Collectors.toList());
        return projectDto;
    }
}
