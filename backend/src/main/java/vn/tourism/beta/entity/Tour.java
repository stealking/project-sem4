package vn.tourism.beta.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "Tour")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "image", length = 50)
    private String image;

    @Column(name = "introduction", length = 300)
    private String introduction;

    @Column(name = "detail", length = 500)
    private String detail;

    @Column(name = "summary", length = 500)
    private String summary;

    @Column(name = "policy", length = 500)
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

    @Column(name = "totalTime", length = 500)
    private String totalTime;

    @ManyToOne
    @JoinColumn(name = "updatedBy", updatable = false, insertable = false, referencedColumnName = "id")
    private User updatedBy;

    @Column(name = "updatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    @Column(name = "enable")
    private Boolean enable = true;

//    @OneToMany(mappedBy="tour")
//    private List<TourDetail> tourDetails;

}