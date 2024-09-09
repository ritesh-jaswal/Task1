package com.example.Task1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customers
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @Column(name = "customer_name",nullable = false,length = 100)
    @Size(max = 100)
    private String customerName;

    @OneToMany(mappedBy = "customers",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Projects> projects = new HashSet<>();
}
