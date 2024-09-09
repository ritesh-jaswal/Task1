package com.example.Task1.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto
{
    private int employeeId;

    private String firstName;

    private String middleName;

    private String lastName;

    private Date dob;

    private String contactNumber;

    private String emergencyContactNumber;

    private String emailId;
}
