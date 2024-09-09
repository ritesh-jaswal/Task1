package com.example.Task1.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto
{
    private int customerId;

    private String customerName;
}
