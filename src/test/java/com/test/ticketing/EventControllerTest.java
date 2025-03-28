package com.test.ticketing;

import com.test.ticketing.model.Events;
import com.test.ticketing.model.Users;
import com.test.ticketing.repository.EventRepository;
import com.test.ticketing.repository.UserRepository;
import com.test.ticketing.service.EventService;
import com.test.ticketing.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventControllerTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private EventService eventService;

    @Test
    void testGetEvent_Success() throws Exception {
        // Arrange
        String name = "Berdendang Bergoyang";

        List<Events> events = Arrays.asList(
                new Events("Berdendang Bergoyang","Senayan",1000, LocalDate.now(),"Event weekend", LocalDateTime.now(),LocalDateTime.now(),false),
                new Events("Syncronice","Senayan",1000,LocalDate.now(),"Event weekend",LocalDateTime.now(),LocalDateTime.now(),false)

        );

        when(eventService.findByNameContaining(name)).thenReturn(List.of(events.get(0)));

        // ACT: Call the service method
        List<Events> result = eventService.findByNameContaining(name);

        // ASSERT: Verify response is a list with correct size and data
        System.out.println(result);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Berdendang Bergoyang", result.get(0).getName());


    }

    @Test
    void testSaveUser() {
        // GIVEN: New event data
        Events newEvent = new Events("Berdendang Bergoyang","Senayan",1000, LocalDate.now(),"Event weekend", LocalDateTime.now(),LocalDateTime.now(),false);


//        when(userRepository.save(any(Users.class))).thenReturn(newUser);
        when(eventService.saveEvents(any(Events.class))).thenReturn(newEvent);

        // 🔹 Call the service method
        Events savedEvents = eventService.saveEvents(newEvent);

        // 🔹 Assert that the returned user matches the expected values
        assertThat(savedEvents.getName()).isEqualTo("Berdendang Bergoyang");

        // 🔹 Verify that the service method was called
        verify(eventService, times(1)).saveEvents(any(Events.class));
    }
}
