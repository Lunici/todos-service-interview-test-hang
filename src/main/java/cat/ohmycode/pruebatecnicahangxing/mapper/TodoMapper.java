package cat.ohmycode.pruebatecnicahangxing.mapper;

import cat.ohmycode.pruebatecnicahangxing.entity.TodoEntity;
import cat.ohmycode.pruebatecnicahangxing.entity.UserEntity;
import cat.ohmycode.pruebatecnicahangxing.form.TodoForm;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {

    public TodoForm toForm(TodoEntity todoEntity) {
        final TodoForm todoForm = new TodoForm();
        todoForm.setId(todoEntity.getId());
        todoForm.setUserId(todoEntity.getUser().getId());
        todoForm.setTitle(todoEntity.getTitle());
        todoForm.setCompleted(todoEntity.getCompleted());
        return todoForm;
    }

    public TodoEntity toEntity(TodoForm todoForm) {
        final TodoEntity todoEntity = new TodoEntity();
        todoEntity.setId(todoForm.getId());
        todoEntity.setUser(new UserEntity(todoForm.getUserId()));
        todoEntity.setTitle(todoForm.getTitle());
        todoEntity.setCompleted(todoForm.isCompleted());
        return todoEntity;
    }

}
