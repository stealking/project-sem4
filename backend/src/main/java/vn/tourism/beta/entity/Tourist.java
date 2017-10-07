package vn.tourism.beta.entity;

import lombok.Data;
import vn.tourism.beta.entity.composedId.TourRegisterId;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@Table(name = "Tourist")
//@IdClass(TourRegisterId.class)
public class Tourist {

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
    @JoinColumn(name = "tourRegisterId", updatable = false, insertable = false, referencedColumnName = "id")
    private TourRegister tourRegister;

    @Column(name = "fullName", length = 500)
    private String fullName;

    @Column(name = "identityNumber", length = 500)
    private String identityNumber;
    
    @Column(name = "address", length = 500)
    private String address;

    @Column(name = "gender", length = 500)
    private String gender;

    @Column(name = "dob")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dob;

    @Column(name = "phone", length = 500)
    private String phone;

    @Column(name = "updatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    @ManyToOne
    @JoinColumn(name = "updatedBy", updatable = false, insertable = false, referencedColumnName = "id")
    private User updatedBy;

    @Column(name = "enable")
    private Boolean enable = true;

}