package vn.tourism.beta.entity.projection;

import org.springframework.data.rest.core.config.Projection;
import vn.tourism.beta.entity.Authority;
import vn.tourism.beta.entity.User;

import java.util.Date;
import java.util.List;

@Projection(name = "withId", types = { User.class })
public interface UserWithId {
    Long getId();
    String getUsername();
    String getPassword();
    String getAvatar();
    String getFirstName();
    String getLastName();
    String getEmail();
    String getAddress();
    String getPhone();
    Date getDob();
    Boolean getEnable();
    Date getLastPasswordResetDate();
    List<Authority> getAuthorities();
}
