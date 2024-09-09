package com.example.Task1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
public class Projects
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id", nullable = false)
    private int projectId;

    @Column(name = "project_name",nullable = false,length = 100)
    @Size(max = 100)
    private String projectName;

    @Column(name = "start_date",nullable = false)
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private Customers customers;

    @ManyToOne
    @JoinColumn(name = "managed_by_id",nullable = false)
    private Employees employees;
}
