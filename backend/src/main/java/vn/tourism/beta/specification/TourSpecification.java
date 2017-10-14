package vn.tourism.beta.specification;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import vn.tourism.beta.entity.Tour;
import vn.tourism.beta.entity.TourDetail;
import vn.tourism.beta.repository.TourRepository2;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.SetJoin;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class TourSpecification {
    @Autowired
    private TourRepository2 tourRepository2;
    private final Log logger = LogFactory.getLog(this.getClass());
    private TourSpecification() {}

    public static Specification<Tour> tourDetailEnabled() {
        // Specification using Java 8 lambdas

        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();
            Join<Tour,TourDetail> tours = root.join("tourDetails", JoinType.INNER);
            predicates.add(cb.equal(tours.get("enable"), true));
            predicates.add(cb.greaterThan(tours.get("departmentDate").as(java.sql.Date.class), new Date((new java.util.Date()).getTime())));
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    
    public static Specification<Tour> departureNameContains(String name) {
        if (name == null) {
            return null;
        } else {
            // Specification using Java 8 lambdas
            return (root, query, cb) -> cb.like(cb.lower(root.join("departure").get("name")), "%"+name.toLowerCase()+"%");
        }
    }

    public static Specification<Tour> journeyNameContains(String name) {
        if (name == null) {
            return null;
        } else {
            // Specification using Java 8 lambdas
            return (root, query, cb) -> cb.like(cb.lower(root.join("journey").get("name")), "%"+name.toLowerCase()+"%");
        }
    }
    public static Specification<Tour> transportIdEquals(String id) {
        if (id == null) {
            return null;
        } else {
            // Specification using Java 8 lambdas
            return (root, query, cb) -> cb.equal(root.join("transport").get("id"), Long.parseLong(id));
        }
    }
    public static Specification<Tour> journeyIdEquals(String id) {
        if (id == null) {
            return null;
        } else {
            // Specification using Java 8 lambdas
            return (root, query, cb) -> cb.equal(root.join("journey").get("id"), Long.parseLong(id));
        }
    }

    public static Specification<Tour> departureIdEquals(String id) {
        if (id == null) {
            return null;
        } else {
            // Specification using Java 8 lambdas
            return (root, query, cb) -> cb.equal(root.join("departure").get("id"), Long.parseLong(id));
        }
    }
    public static Specification<Tour> tourTypeIdEquals(String id) {
        if (id == null) {
            return null;
        } else {
            // Specification using Java 8 lambdas
            return (root, query, cb) -> cb.equal(root.join("tourType").get("id"), Long.parseLong(id));
        }
    }
    public static Specification<Tour> priceIn(String price) {
        if (price == null) {
            return null;
        } else {
            String[]prices = price.split(",");
            String minPrice = prices[0];
            String maxPrice = prices[1];
            // Specification using Java 8 lambdas
            return (root, query, cb) -> cb.between(root.get("price"), Long.parseLong(minPrice), Long.parseLong(maxPrice));
        }
    }
    public static Specification<Tour> totalTimeIn(String totalTime) {
        if (totalTime == null) {
            return null;
        } else {
            String[]prices = totalTime.split(",");
            String minTotalTime = prices[0];
            String maxTotalTime = prices[1];
            // Specification using Java 8 lambdas
            return (root, query, cb) -> cb.between(root.get("totalTime"), Double.parseDouble(minTotalTime), Double.parseDouble(maxTotalTime));
        }
    }
    public static Specification<Tour> saleOff(String saleOff) {
        if (saleOff == null) {
            return null;
        } else {
            // Specification using Java 8 lambdas
            return (root, query, cb) -> cb.equal(root.get("saleOff"), "1".equals(saleOff));
        }
    }
    public  Iterable<Tour> findAllQueryName(Map<String, String> filter, Pageable pageable) {
        return tourRepository2.findAll(
                Specifications
                        .where(departureNameContains(filter.get("departureName")))
                        .and(journeyNameContains(filter.get("journeyName")))
                        .and(tourTypeIdEquals(filter.get("tourTypeId")))
                        .and(priceIn(filter.get("price")))
                        .and(saleOff(filter.get("saleOff")))
                ,pageable
        )
        ;
    }
    public  Iterable<Tour> findAllQueryId(Map<String, String> filter, Pageable pageable) {
        return tourRepository2.findAll(
                Specifications
//                        .where(tourDetailEnabled())
                        .where(departureIdEquals(filter.get("departureId")))
                        .and(journeyIdEquals(filter.get("journeyId")))
                        .and(tourTypeIdEquals(filter.get("tourTypeId")))
                        .and(transportIdEquals(filter.get("transportId")))
                        .and(priceIn(filter.get("price")))
                        .and(totalTimeIn(filter.get("totalTime")))
                        .and(saleOff(filter.get("saleOff")))
                ,pageable
        )
                ;
    }
}
