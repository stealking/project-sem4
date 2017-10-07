package vn.tourism.beta.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "Voucher")
public class Voucher {

    public Voucher(){}

    public Voucher(Long id){
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "discountPercent")
    private Long discountPercent;

    @Column(name = "discountMoney")
    private Long discountMoney;

    @Column(name = "image")
    private String image;

    @Column(name = "introduction", length = 300)
    @Size(min = 4, max = 300)
    private String introduction;

    @Column(name = "content", length = 500)
    @Size(min = 4, max = 500)
    private String content;

    @Column(name = "expiredOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiredOn;

    @ManyToOne
    @JoinColumn(name = "updatedBy", updatable = false, insertable = false, referencedColumnName = "id")
    private User updatedBy;

    @Column(name = "updatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    @Column(name = "enable")
    private Boolean enable = true;

//    @OneToMany(mappedBy="voucher")
//    private List<TourDetail> tourDetails;

}