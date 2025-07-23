package cat.ohmycode.pruebatecnicahangxing.service;

import cat.ohmycode.pruebatecnicahangxing.entity.TodoEntity;
import cat.ohmycode.pruebatecnicahangxing.form.TodoForm;
import cat.ohmycode.pruebatecnicahangxing.mapper.TodoMapper;
import cat.ohmycode.pruebatecnicahangxing.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class TodoService {

    private TodoRepository todoRepository;
    private TodoMapper todoMapper;

    @Autowired
    public TodoService(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    public Page<TodoEntity> findByTitleAndUsername(final String title, final String username, final Pageable pageable) {
        return todoRepository.findAllByTitleAndUsername(title, username, pageable);
    }

    public TodoEntity save(final TodoEntity todoEntity) {
        return todoRepository.save(todoEntity);
    }

    public TodoEntity saveForm(final TodoForm todoForm) {
        return save(todoMapper.toEntity(todoForm));
    }

    public TodoEntity findById(final Integer id) {
        return todoRepository.findById(id).orElse(null);
    }

    public TodoForm findFormById(final Integer id) {
        return todoMapper.toForm(findById(id));
    }

    public void deleteById(final Integer id) {
        todoRepository.deleteById(id);
    }

    public boolean isOwner(final Integer todoId, String username) {
        if (todoId == null) {
            return true;
        }

        final TodoEntity todo = findById(todoId);
        if (todo == null) {
            return false;
        }
        return todo.getUser().getUsername().equals(username);
    }
}
