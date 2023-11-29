package com.example.Diplomna.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String userName;
    private String email;
    @Column(nullable = true)
    private String password;
    @OneToOne
    @JoinColumn(name = "userRoleId")
    private UserRole userRole;
    @OneToOne
    @JoinColumn(name = "logoId")
    private File file;
    private boolean isActivated;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                ", file=" + file +
                ", isActivated=" + isActivated +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public User(Long id, String userName, String email, String password, UserRole userRole, File file, boolean isActivated) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.file = file;
        this.isActivated = isActivated;
    }
}
