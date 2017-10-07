package vn.tourism.beta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "username", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String username;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "avatar", length = 300)
    @Size(min = 4, max = 300)
    private String avatar;


    @Column(name = "firstName", length = 50)
    private String firstName;

    @Column(name = "lastName", length = 50)
    private String lastName;

    @Column(name = "email", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String email;

    @Column(name = "address", length = 50)
    private String address;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "dob")
    private Date dob;



    @Column(name = "updatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    @Column(name = "enable")
    @NotNull
    private Boolean enable = true;

    @Column(name = "lastPasswordResetDate")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastPasswordResetDate = new Date();

//    @OneToMany(mappedBy = "user", fetch=FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//    public List<UserAuthority> userAuthorities;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
    private List<Authority> authorities;
//
//    @OneToMany(mappedBy="user")
//    private List<TourRegister> tourRegisters;
//
//    @OneToMany(mappedBy="user")
//    private List<TourDetail> tourDetails;

//    @OneToMany(mappedBy="updateBy")
//    private List<Tour> tours;

//    @OneToMany(mappedBy="user")
//    private List<TourType> tourTypes;

//    @OneToMany(mappedBy="user")
//    private List<Voucher> vouchers;


//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", dob=" + dob +
                ", updatedOn=" + updatedOn +
                ", enable=" + enable +
                ", lastPasswordResetDate=" + lastPasswordResetDate +
                ", authorities=" + authorities +
                '}';
    }
}