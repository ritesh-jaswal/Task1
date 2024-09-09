package com.example.Task1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
public class Employees
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id",nullable = false)
    private int employeeId;

    @Column(name = "first_name",nullable = false,length = 100)
    @Size(max = 100)
    private String firstName;

    @Column(name = "middle_name",length = 100)
    @Size(max = 100)
    private String middleName;

    @Column(name = "last_name",nullable = false,length = 100)
    @Size(max = 100)
    private String lastName;

    @Column(name = "dob",nullable = false)
    private Date dob;

    @Column(name = "contact_number",nullable = false,length = 10)
    @Size(max = 10)
    private String contactNumber;

    @Column(name = "emergency_contact_number",length = 10)
    @Size(max = 10)
    private String emergencyContactNumber;

    @Column(name = "email_id",nullable = false,length = 50)
    @Size(max = 50)
    private String emailId;

    @OneToMany(mappedBy = "employees",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Projects> projects = new HashSet<>();
}
