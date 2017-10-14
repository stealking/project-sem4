package vn.tourism.beta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @JoinColumn(name = "tourDetailId")
    private TourDetail tourDetail;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "representativeId")
    private User representative;

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

//    @Override
//    public String toString() {
//        return "TourRegister{" +
//                "id=" + id +
//                ", tourDetail=" + tourDetail +
//                ", numberAdult=" + numberAdult +
//                ", numberChildren=" + numberChildren +
//                ", numberBaby=" + numberBaby +
//                ", registeredOn=" + registeredOn +
//                ", enable=" + enable +
//                '}';
//    }
}