package vn.tourism.beta.service;

import org.springframework.data.domain.Sort;
import vn.tourism.beta.entity.User;

import java.util.List;

public interface UserService {
    public User updateUserById(User user, Long userId);
    public User deleteUserById( Long userId);
    public List<User> getPage(int pageNumber, Sort.Direction sortDirection,String column, int maxResult);
}
