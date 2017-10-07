package vn.tourism.beta.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import vn.tourism.beta.enums.AuthorityName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Table(name = "Authority")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthorityName name;

//    @OneToMany(mappedBy = "authority", fetch=FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnore
//    public List<UserAuthority> userAuthorities;

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
    //    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
//    private List<User> users;

}