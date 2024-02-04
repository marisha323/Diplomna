package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class File implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String type;

    private String path;

    public File() {

    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public File(Long id, String type, String path) {
        this.id = id;
        this.type = type;
        this.path = path;
    }
}
