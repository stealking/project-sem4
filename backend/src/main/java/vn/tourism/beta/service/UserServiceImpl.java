package vn.tourism.beta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.tourism.beta.entity.User;
import vn.tourism.beta.repository.UserRepository;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User updateUserById(User user, Long userId) {
        User userDb = userRepository.findOne(userId);
        if (user.getAddress() != null) userDb.setAddress(user.getAddress());
        if (user.getPhone() != null) userDb.setPhone(user.getPhone());
        if (user.getAvatar() != null) userDb.setAvatar(user.getAvatar());
        if (user.getDob() != null) userDb.setDob(user.getDob());
        if (user.getFirstName() != null) userDb.setFirstName(user.getFirstName());
        if (user.getLastName() != null) userDb.setLastName(user.getLastName());
        if (user.getUpdatedOn() != null) userDb.setUpdatedOn(user.getUpdatedOn());
//         userDb.setUser();
        userRepository.save(userDb);
        return userDb;
    }

    @Override
    public User deleteUserById(Long userId) {
        User userDb = userRepository.findOne(userId);
        userDb.setEnable(false);
        userRepository.save(userDb);
        return userDb;
    }
    @Override
    public List<User>getPage(int pageNumber, Sort.Direction sortDirection,String column,  int maxResult){
        PageRequest request = new PageRequest(pageNumber - 1, maxResult, sortDirection, column);

        return userRepository.findAll(request).getContent();
    }
}
