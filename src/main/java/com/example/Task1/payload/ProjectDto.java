package com.example.Task1.payload;

import com.example.Task1.model.Customers;
import com.example.Task1.model.Employees;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDto
{
    private int projectId;

    private String projectName;

    private Date startDate;

    private Date endDate;

    private CustomerDto customers;

    private EmployeeDto employees;
}
