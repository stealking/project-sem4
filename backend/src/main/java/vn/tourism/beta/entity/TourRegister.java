package vn.tourism.beta.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "TourRegister", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"representativeId", "tourDetailId"})
})
//@IdClass(TourRegisterId.class)
public class TourRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name="tourDetailId")
//    private TourDetail tourDetail;
//
//    @Id
//    private long userId;
//    @Id
//    private long tourDetailId;


    @ManyToOne
    @JoinColumn(name = "tourDetailId", updatable = false, insertable = false, referencedColumnName = "id")
    private TourDetail tourDetail;

    @ManyToOne
    @JoinColumn(name = "representativeId", updatable = false, insertable = false, referencedColumnName = "id")
    private User Representative;

    @Column(name = "numberAdult")
    private Long numberAdult;

    @Column(name = "numberChildren")
    private Long numberChildren;

    @Column(name = "numberBaby")
    private Long numberBaby;
    
    @Column(name = "registeredOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredOn;

    @Column(name = "enable")
    private Boolean enable = true;
}