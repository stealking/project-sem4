package vn.tourism.beta.service;

import org.springframework.data.domain.Sort;
import vn.tourism.beta.entity.Tourist;

import java.util.List;

public interface TouristService {
    public Tourist updateTouristById(Tourist tourist, Long touristId);
    public Tourist deleteTouristById(Long touristId);
    public List<Tourist> getPage(int pageNumber, Sort.Direction sortDirection, String column, int maxResult);
}
