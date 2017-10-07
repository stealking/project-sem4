package vn.tourism.beta.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "TourDetail")
public class TourDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name="tourId", nullable = false)
    private Tour tour;

    @ManyToOne
    @JoinColumn(name="voucherId")
    private Voucher voucher;

    @Column(name = "details", length = 500)
    private String details;

    @Column(name = "priceAdult")
    private Long priceAdult;

    @Column(name = "priceChildren")
    private Long priceChildren;

    @Column(name = "priceBaby")
    private Long priceBaby;

    @Column(name = "exCost")
    private Long exCost;

    @Column(name = "departmentDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date departmentDate;

    @ManyToOne
    @JoinColumn(name = "updatedBy", referencedColumnName = "id")
    private User updatedBy;

    @Column(name = "updatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    @Column(name = "enable")
    private Boolean enable = true;;

//    @OneToMany(mappedBy="tourDetail")
//    private List<TourRegister> tourRegisters;
}