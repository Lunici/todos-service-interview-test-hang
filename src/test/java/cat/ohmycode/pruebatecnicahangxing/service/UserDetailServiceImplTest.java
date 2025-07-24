package cat.ohmycode.pruebatecnicahangxing.service;

import cat.ohmycode.pruebatecnicahangxing.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserDetailServiceImplTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserDetailServiceImpl userDetailServiceImpl;

    @Test
    public void loadUserByUsernameTest() {
        UserEntity userMock = new UserEntity();
        userMock.setId(1);
        userMock.setName("Administrator");
        userMock.setUsername("admin");
        userMock.setPassword("12345");

        when(userService.findByUsername("admin")).thenReturn(userMock);

        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername("admin");

        assertEquals("admin", userDetails.getUsername());
        assertEquals("12345", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")));

    }

}
