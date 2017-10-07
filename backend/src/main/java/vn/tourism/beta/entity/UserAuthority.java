package vn.tourism.beta.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import vn.tourism.beta.entity.composedId.UserAuthorityId;
import vn.tourism.beta.enums.AuthorityName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

//@Entity
@Data
@Table(name = "UserAuthority")
public class UserAuthority implements Serializable {


    @Id
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="userId")
    @JsonIgnore
    User user;

    @Id
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="authorityId")
    Authority authority;
//    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
//    private List<User> users;


    @Override
    public String toString() {
        return "UserAuthority{" +
                "user=" + user +
                ", authority=" + authority +
                '}';
    }
}