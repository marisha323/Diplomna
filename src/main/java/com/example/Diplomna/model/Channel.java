package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Channel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    @OneToOne
    @JoinColumn(name = "ownerId")
    private User user;
    @OneToOne
    @JoinColumn(name = "logoId")
    private File file;

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", user=" + user +
                ", file=" + file +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Channel(Long id, User user, File file) {
        this.id = id;
        this.user = user;
        this.file = file;
    }
}
