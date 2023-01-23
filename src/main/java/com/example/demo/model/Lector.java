package com.example.demo.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lectors")
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "salary")
    private float salary;

    @Enumerated(EnumType.STRING)
    @Column(name = "degree", length = 20)
    private Degree degree;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "department_lector",
            joinColumns = { @JoinColumn(name = "lector_id") },
            inverseJoinColumns = { @JoinColumn(name = "department_id") }
    )
    private Set<Department> departments = new HashSet<>();

    @OneToOne(mappedBy = "head",fetch = FetchType.LAZY)
    private Department department;

    public Lector() {
    }

    public Lector(String firstName, String lastName, float salary, Degree degree, Set<Department> departments) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.degree = degree;
        this.departments = departments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public Set<Department> getDepartments() {
        return departments;
    }


    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

}
