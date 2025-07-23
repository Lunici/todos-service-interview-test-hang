package cat.ohmycode.pruebatecnicahangxing.controller;

import cat.ohmycode.pruebatecnicahangxing.entity.TodoEntity;
import cat.ohmycode.pruebatecnicahangxing.service.TodoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodosController {

    private TodoService todoService;

    public TodosController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("")
    public String home(@RequestParam(required = false) String title,
                       @RequestParam(required = false) String username,
                       @RequestParam(required = false, defaultValue = "1") Integer page,
                       @RequestParam(required = false) String sortField,
                       @RequestParam(required = false) String sortDirection,
                       Model model) {

        Pageable pageRequest = StringUtils.isBlank(sortField) || StringUtils.isBlank(sortDirection)
                ? PageRequest.of(page - 1, 10)
                : PageRequest.of(page - 1, 10, Sort.Direction.fromString(sortDirection), sortField);
        Page<TodoEntity> pageResponse = todoService.findByTitleAndUsername(title, username, pageRequest);
        model.addAttribute("todos", pageResponse.getContent());
        model.addAttribute("title", title);
        model.addAttribute("username", username);
        model.addAttribute("totalPages", pageResponse.getTotalPages());

        model.addAttribute("page", page);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection != null && sortDirection.equals("asc") ? "desc" : "asc");

        model.addAttribute("ascSymbol", "↑");
        model.addAttribute("descSymbol", "↓");

        return "todos";
    }

    @GetMapping("/todo/delete")
    @PreAuthorize("@todoService.isOwner(#todoId, authentication.name)")
    public String delete(@RequestHeader(value = "Referer", required = false) String referer, @RequestParam Integer todoId) {
        todoService.deleteById(todoId);
        return "redirect:" + referer;
    }
}
