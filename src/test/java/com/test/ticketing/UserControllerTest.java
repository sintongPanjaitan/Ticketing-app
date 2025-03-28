package com.test.ticketing;



import com.test.ticketing.model.Users;
import com.test.ticketing.repository.UserRepository;
import com.test.ticketing.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @Test
    void testGetUser_Success() throws Exception {
        // Arrange
        String name = "Raisa";
        List<Users> users = Arrays.asList(
                new Users("Raisa","1212094124124","0813343434343", LocalDateTime.now(),LocalDateTime.now(),false),
                new Users("Judika","1212094124121","0813343434342", LocalDateTime.now(),LocalDateTime.now(),false)
        );

        when(userService.findByNameContaining(name)).thenReturn(List.of(users.get(0)));

        // ACT: Call the service method
        List<Users> result = userService.findByNameContaining(name);

        // ASSERT: Verify response is a list with correct size and data
        System.out.println(result);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Raisa", result.get(0).getName());

//        // VERIFY: Ensure findByName was called once
//        verify(userRepository, times(1)).findByNameContaining(name);
    }

    @Test
    void testSaveUser() {
        // GIVEN: New user data
        Users newUser = new Users("Raisa","1212094124124","0813343434343", LocalDateTime.now(),LocalDateTime.now(),false);
//        Users savedUser = new Users("Raisa","1212094124124","0813343434343", LocalDateTime.now(),LocalDateTime.now(),false);


//        when(userRepository.save(any(Users.class))).thenReturn(newUser);
        when(userService.saveUser(any(Users.class))).thenReturn(newUser);

        // 🔹 Call the service method
        Users savedUser = userService.saveUser(newUser);

        // 🔹 Assert that the returned user matches the expected values
        assertThat(savedUser.getName()).isEqualTo("Raisa");

        // 🔹 Verify that the service method was called
        verify(userService, times(1)).saveUser(any(Users.class));
    }

}
