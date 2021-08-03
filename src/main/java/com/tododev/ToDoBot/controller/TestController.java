package com.tododev.ToDoBot.controller;

import com.tododev.ToDoBot.model.*;
import com.tododev.ToDoBot.repository.ApplicationUserRepository;
import com.tododev.ToDoBot.repository.BoardListRepository;
import com.tododev.ToDoBot.repository.SectionRepository;
import com.tododev.ToDoBot.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
//
//    @GetMapping("/newBoard")
//    public String getTheBoard(Principal principal , Model model) {
//        model.addAttribute("board",applicationUserRepository.findApplicationUserByUsername(principal.getName()).getBoardLists());
//        return "myboards";
//    }


    @PostMapping("/newBoard")
    public RedirectView newBoard( Principal principal, @RequestParam String boardName, String description) {
        ApplicationUser user = applicationUserRepository.findApplicationUserByUsername(principal.getName());
        boardListRepository.save(new BoardList(boardName, description, user));
        return new RedirectView("/myboards");

    }

    @GetMapping("/board/{id}")
    public String getMyBoard(@PathVariable long id, Model model) {
        BoardList boardList = boardListRepository.findById(id).get();
        model.addAttribute("boardList", boardList);
        model.addAttribute("sec2",boardList.getSections());
        return "board";
    }

    @PostMapping("/newSection/{id}")
    public RedirectView newSection(@PathVariable Long id, @RequestParam String title,Model model) {
        BoardList boardList = boardListRepository.getById(id);
        sectionRepository.save(new Section(title, boardList));
        return new RedirectView("/board/" + id);
    }


    @PostMapping("/newTask/{id}")
    public RedirectView newTask(@RequestParam String taskName, String description, @PathVariable Long id) {
        Section section = sectionRepository.getById(id);
        taskRepository.save(new Task(taskName, description, section));
        long boardId = section.getBoardList().getId();
        return new RedirectView("/board/" + boardId);
    }

    @ResponseBody
    @RequestMapping(value = "/newTask/{id}/moveTask", method = RequestMethod.POST)
    public void moveTask(@PathVariable Long id, @RequestBody MovedTask data) {
        Section section = sectionRepository.getById(id);
        taskRepository.updateOnMove(data.getTaskId(), section);
    }
//html post for move task
}
