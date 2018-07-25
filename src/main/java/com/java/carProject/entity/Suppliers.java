package com.java.carProject.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="suppliers")
public class Suppliers {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private boolean isImporter;
    @OneToMany(targetEntity =Parts.class, mappedBy = "suppliers", fetch=FetchType.EAGER)
    private Set<Parts> parts;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }

    public Set<Parts> getParts() {
        return parts;
    }

    public void setParts(Set<Parts> parts) {
        this.parts = parts;
    }


}
