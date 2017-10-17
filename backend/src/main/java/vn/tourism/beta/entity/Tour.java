package vn.tourism.beta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "Tour")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "price")
    private Long price;

    @Column(name = "saleOff")
    private Boolean saleOff;

    @Column(name = "image")
    private String image;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "detail")
    private String detail;

    @Column(name = "summary")
    private String summary;

    @Column(name = "policy")
    private String policy;


    @OneToOne
    @JoinColumn(name="tourTypeId")
    private TourType tourType;

    @OneToOne
    @JoinColumn(name="departureId")
    private Departure departure;

    @OneToOne
    @JoinColumn(name="journeyId")
    private Journey journey;

    @OneToOne
    @JoinColumn(name="transportId")
    private Transport transport;

    @Column(name = "totalTime")
    private Long totalTime;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "updatedBy", updatable = false, insertable = false, referencedColumnName = "id")
    private User updatedBy;

    @Column(name = "updatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    @Column(name = "enable")
    private Boolean enable = true;

    @OneToMany(mappedBy="tour")
    private List<TourDetail> tourDetails;

}