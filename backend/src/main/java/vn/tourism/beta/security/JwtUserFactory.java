package vn.tourism.beta.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import vn.tourism.beta.entity.Authority;
import vn.tourism.beta.entity.User;
import vn.tourism.beta.entity.UserAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
//        List<Authority> authorities = new ArrayList<>();
//        List<UserAuthority> userAuthorities = user.getUserAuthorities();
//        for (UserAuthority userAuthority: userAuthorities) {
//            authorities.add(userAuthority.getAuthority());
//        }
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getAuthorities()),
                user.getEnable(),
                user.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
    }
}
