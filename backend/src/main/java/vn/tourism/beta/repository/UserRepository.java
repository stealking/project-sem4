package vn.tourism.beta.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import vn.tourism.beta.entity.User;
import vn.tourism.beta.entity.projection.UserWithId;

import java.util.List;

/**
 * Created by stephan on 20.03.16.
 */
//@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:8787"},
//        maxAge = 4800, allowCredentials = "false")
@RepositoryRestResource(excerptProjection = UserWithId.class)
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    Long countAllByEnableEquals(Boolean enable);
    User findByIdAndEnableEquals(Long id, Boolean enable);
    Page<User> findAllByEnableEquals(Pageable pageable, Boolean enable);
    Page<User> findAllByAuthorities_idIn (Pageable pageable, List<Long> id);
    Page<User> findByEmailIgnoreCaseContainingAndEnableEquals(Pageable pageable, String email, Boolean enable);
    Page<User> findByUsernameIgnoreCaseContainingAndEnableEquals(Pageable pageable, String username, Boolean enable);
    Page<User> findByAddressIgnoreCaseContainingAndEnableEquals(Pageable pageable, String address, Boolean enable);
    Page<User> findByPhoneContainingAndEnableEquals(Pageable pageable, String phone, Boolean enable);
}
