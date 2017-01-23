package ru.stqa.pft.mantis.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by admin on 23.01.2017.
 */
@Entity
@Table(name = "mantis_user_table")
public class UserData {
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "username")
    private String userName;

    @Expose
    @Column(name = "email")
    private String email;

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public UserData withId(int id) {
        this.id = id;
        return this;
    }

    public UserData withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }
}
