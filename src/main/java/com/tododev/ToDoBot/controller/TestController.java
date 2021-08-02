package com.tododev.ToDoBot.controller;

import com.tododev.ToDoBot.model.ApplicationUser;
import com.tododev.ToDoBot.model.BoardList;
import com.tododev.ToDoBot.model.Section;
import com.tododev.ToDoBot.model.Task;
import com.tododev.ToDoBot.repository.ApplicationUserRepository;
import com.tododev.ToDoBot.repository.BoardListRepository;
import com.tododev.ToDoBot.repository.SectionRepository;
import com.tododev.ToDoBot.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    BoardListRepository boardListRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/newBoard")
    public String getTheBoard(Principal principal , Model model) {
        model.addAttribute("board",applicationUserRepository.findApplicationUserByUsername(principal.getName()).getBoardLists());
        return "myboards";
    }

    @PostMapping("/newBoard")
    public RedirectView newBoard(Principal principal, @RequestParam String boardName, String description) {
        ApplicationUser user = applicationUserRepository.findApplicationUserByUsername(principal.getName());
        boardListRepository.save(new BoardList(boardName, description, user));
        return new RedirectView("/newBoard");
    }

    @PostMapping("/newSection")
    public RedirectView newSection(@RequestParam  Long id, @RequestParam String title) {
        BoardList boardList = boardListRepository.getById(id);
        sectionRepository.save(new Section(title, boardList));
        return new RedirectView("/newBoard");
    }


    @PostMapping("/newTask")
    public RedirectView newTask(@RequestParam String taskName , String description ,Long id){
      Section section = sectionRepository.getById(id);
      taskRepository.save(new Task(taskName,description,section));
        return new RedirectView("/newBoard");
    }

}
