package vn.tourism.beta.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import vn.tourism.beta.entity.Tour;
import vn.tourism.beta.entity.TourType;

/**
 * Created by stephan on 20.03.16.
 */
public interface TourRepository2 extends JpaRepository<Tour, Long>, JpaSpecificationExecutor<Tour> {
}
