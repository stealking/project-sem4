package vn.tourism.beta.specification;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import vn.tourism.beta.entity.Tour;
import vn.tourism.beta.repository.TourRepository2;

import java.util.Map;
import java.util.logging.Logger;

@Service
public class TourSpecification {
    @Autowired
    private TourRepository2 tourRepository2;
    private final Log logger = LogFactory.getLog(this.getClass());
    private TourSpecification() {}
    public static Specification<Tour> departureNameContains(String name) {
        if (name == null) {
            return null;
        } else {
            // Specification using Java 8 lambdas
            return (root, query, cb) -> cb.like(cb.lower(root.join("departure").get("name")), "%"+name.toLowerCase()+"%");
        }
    }
    public static Specification<Tour> routeNameContains(String name) {
        if (name == null) {
            return null;
        } else {
            // Specification using Java 8 lambdas
            return (root, query, cb) -> cb.like(cb.lower(root.join("route").get("name")), "%"+name.toLowerCase()+"%");
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
    public static Specification<Tour> tourTypeIdEquals(String id) {
        if (id == null) {
            return null;
        } else {
            // Specification using Java 8 lambdas
            return (root, query, cb) -> cb.equal(root.join("tourType").get("id"), Long.parseLong(id));
        }
    }

    public  Iterable<Tour> findAllQuery(Map<String, String> filter, Pageable pageable) {
        return tourRepository2.findAll(
                Specifications
                        .where(departureNameContains(filter.get("departureName")))
                        .and(routeNameContains(filter.get("routeName")))
                        .and(journeyNameContains(filter.get("journeyName")))
                        .and(tourTypeIdEquals(filter.get("tourTypeId")))
                ,pageable
        )
        ;
    }
}
