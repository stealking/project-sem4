package vn.tourism.beta.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "Banner")
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "image", length = 50)
    private String image;

    @Column(name = "introduction", length = 300)
    private String introduction;

    @Column(name = "content", length = 500)
    private String content;

    @Column(name = "href", length = 500)
    private String href;

//    @Column(name = "expiredOn")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date expiredOn;

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