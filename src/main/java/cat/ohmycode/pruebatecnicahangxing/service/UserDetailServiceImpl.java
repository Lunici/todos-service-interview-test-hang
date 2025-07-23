package cat.ohmycode.pruebatecnicahangxing.service;

import cat.ohmycode.pruebatecnicahangxing.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UserService userService;

    @Autowired
    public UserDetailServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity userEntity = userService.findByUsername(username);
        final SimpleGrantedAuthority authorities = new SimpleGrantedAuthority("ROLE_" + userEntity.getUsername().toUpperCase());
        return  new User(userEntity.getUsername(), userEntity.getPassword(), List.of(authorities));
    }
}
