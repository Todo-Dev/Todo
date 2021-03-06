package com.tododev.ToDoBot.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.sql.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import javax.persistence.*;
import java.util.*;

@Entity
public class ApplicationUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size( max = 250)
    private String bio ;
    @NotEmpty()
    @Size(max = 25)
    @Column(unique = true, nullable = false)
    private String username;

    @NotEmpty()
    @Size(min = 8)
    @Column(nullable = false)
    private String password;

    @NotEmpty()
    @Size(max = 20)
    @Column(nullable = true, length = 10)
    private String firstName;

    @NotEmpty()
    @Size(max = 20)
    @Column(nullable = true, length = 10)
    private String lastName;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @NotEmpty()
    @Size(max = 25)
    @Column(unique = true, nullable = true)
    private String email;
    private Date dateOfBirth;
    private String profession;
    @Column(nullable = true, length = 64)
    private String photos;
    @ManyToMany(mappedBy = "friends")
    Set<ApplicationUser> user = new HashSet<>();

    @ManyToMany()
    @JoinTable(
            name = "friends",
            joinColumns = { @JoinColumn (name = "user_Id")},
            inverseJoinColumns = {@JoinColumn(name = "friend_Id")}
    )
     Set<ApplicationUser> friends = new HashSet<>();

    public Set<ApplicationUser> getFriends() {
        return friends;
    }

    @OneToMany(mappedBy = "applicationUser" )
    @OrderBy("id")
    List<BoardList> boardLists;

    public ApplicationUser(String username, String password, String firstName, String lastName, String email, Date dateOfBirth, String profession , String bio ) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.profession = profession;
        this.bio = bio;
    }

    public ApplicationUser() {
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public List<BoardList> getBoardLists() {
        return boardLists;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhotos() {
        return photos;
    }

    public Long getId() {
        return id;
    }
    @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;

        return "/user-photos/" + id + "/" + photos;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getProfession() {
        return profession;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}