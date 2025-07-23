package cat.ohmycode.pruebatecnicahangxing.controller;

import cat.ohmycode.pruebatecnicahangxing.entity.TodoEntity;
import cat.ohmycode.pruebatecnicahangxing.form.TodoForm;
import cat.ohmycode.pruebatecnicahangxing.service.TodoService;
import cat.ohmycode.pruebatecnicahangxing.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
public class EditionController {

    private TodoService todoService;
    private UserService userService;

    public EditionController(TodoService todoService, UserService userService) {
        this.todoService = todoService;
        this.userService = userService;
    }

    @GetMapping("/edition")
    public String edition(@RequestParam(required = false) Integer todoId, Model model) {
        TodoForm todoForm = todoId == null ? new TodoForm() : todoService.findFormById(todoId);
        if (todoForm == null) {
            todoForm = new TodoForm();
        }
        model.addAttribute("todoForm", todoForm);
        model.addAttribute("allUsers", userService.findAll());
        model.addAttribute("errors", Collections.emptyList());
        return "edition";
    }

    @GetMapping("/save")
    public String save(@ModelAttribute TodoForm todoForm, Model model) {
        final List<String> errors = new ArrayList<>();
        if (todoForm.getUserId() == 0) {
            errors.add("Please select a user");
        }
        if (todoForm.getTitle().isBlank()) {
            errors.add("The title is required");
        } else if (todoForm.getTitle().length() > 200) {
            errors.add("The title is too long. Maximum length is 200");
        }

        if (errors.isEmpty()) {
            todoService.saveForm(todoForm);
            return "redirect:/";
        }

        model.addAttribute("todoForm", todoForm);
        model.addAttribute("allUsers", userService.findAll());
        model.addAttribute("errors", errors);
        return "edition";
    }

}
