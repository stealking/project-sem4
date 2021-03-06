package vn.tourism.beta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "TourType")
public class TourType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name")
    @Size(min = 4, max = 300)
    private String name;

    @ManyToOne
    @JoinColumn(name = "updatedBy", updatable = false, insertable = false, referencedColumnName = "id")
    private User updatedBy;

    @Column(name = "updatedOn")
    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    @Column(name = "enable",columnDefinition = "boolean default true",nullable = false)
    @NotNull
    private boolean enable = true;

//    @OneToMany(mappedBy="tourType")
//    private List<Tour> tours;
}