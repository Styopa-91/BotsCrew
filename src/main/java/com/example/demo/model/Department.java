package com.example.demo.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name = "dep_name")
    private String depName;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "lector_id", referencedColumnName = "id")
    private Lector head;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "departments", cascade = CascadeType.ALL)
    Set<Lector> lectors = new HashSet<>();

    public Department() {
    }

    public Department(String depName, Lector head, Set<Lector> lectors) {
        this.depName = depName;
        this.head = head;
        this.lectors = lectors;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Lector getHead() {
        return head;
    }

    public void setHead(Lector head) {
        this.head = head;
    }

    public Set<Lector> getLectors() {
        return lectors;
    }

    public void setLectors(Set<Lector> lectors) {
        this.lectors = lectors;
    }
}
