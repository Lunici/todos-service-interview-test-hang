package cat.ohmycode.pruebatecnicahangxing.service;

import cat.ohmycode.pruebatecnicahangxing.entity.TodoEntity;
import cat.ohmycode.pruebatecnicahangxing.entity.UserEntity;
import cat.ohmycode.pruebatecnicahangxing.mapper.TodoMapper;
import cat.ohmycode.pruebatecnicahangxing.repository.TodoRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private TodoMapper todoMapper;

    @InjectMocks
    private TodoService todoService;

    @Test
    public void isOwnerTest() {
        UserEntity userMock = new UserEntity();
        userMock.setId(20);
        userMock.setUsername("admin");

        TodoEntity todoMock = new TodoEntity();
        todoMock.setId(1);
        todoMock.setTitle("title");
        todoMock.setUser(userMock);
        todoMock.setCompleted(true);

        userMock.setTodos(List.of(todoMock));

        when(todoRepository.findById(1)).thenReturn(Optional.of(todoMock));

        assertTrue(todoService.isOwner(null, "any"));
        assertTrue(todoService.isOwner(1, "admin"));
        assertFalse(todoService.isOwner(1, "any"));

    }

}
