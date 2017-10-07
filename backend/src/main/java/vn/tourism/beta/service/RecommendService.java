package vn.tourism.beta.service;

import org.springframework.data.domain.Sort;
import vn.tourism.beta.entity.Recommend;

import java.util.List;

public interface RecommendService {
    public Recommend updateRecommendById(Recommend recommend, Long recommendId);
    public Recommend deleteRecommendById(Long recommendId);
    public List<Recommend> getPage(int pageNumber, Sort.Direction sortDirection, String column, int maxResult);
}
