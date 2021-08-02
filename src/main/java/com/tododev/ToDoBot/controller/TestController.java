//package com.tododev.ToDoBot.controller;
//
//import com.tododev.ToDoBot.model.ApplicationUser;
//import com.tododev.ToDoBot.model.BoardList;
//import com.tododev.ToDoBot.model.Section;
//import com.tododev.ToDoBot.repository.ApplicationUserRepository;
//import com.tododev.ToDoBot.repository.BoardListRepository;
//import com.tododev.ToDoBot.repository.SectionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.view.RedirectView;
//
//import java.security.Principal;
//import java.util.List;
//
//@Controller
//public class TestController {
//    @Autowired
//    ApplicationUserRepository applicationUserRepository;
//
//    @Autowired
//    BoardListRepository boardListRepository;
//
//    @Autowired
//    SectionRepository sectionRepository;
//
//    @GetMapping("/newBoard")
//    public String getTheBoard(Principal principal , Model model) {
//        model.addAttribute("board",applicationUserRepository.findApplicationUserByUsername(principal.getName()).getBoardLists());
//        return "myboards";
//    }
//
//    @PostMapping("/newBoard")
//    public RedirectView newBoard(Principal principal, @RequestParam String boardName, String description) {
//        ApplicationUser user = applicationUserRepository.findApplicationUserByUsername(principal.getName());
//        boardListRepository.save(new BoardList(boardName, description, user));
//        return new RedirectView("/newBoard");
//    }
//
//    @PostMapping("/newSection")
//    public RedirectView newSection(@RequestParam  Long id, @RequestParam String title) {
//        BoardList boardList = boardListRepository.getById(id);
//        sectionRepository.save(new Section(title, boardList));
//        return new RedirectView("/newBoard");
//    }
//
//
//    // need board Id
////    @PostMapping("/newTask")
////    public RedirectView newTask(Principal principal ,@RequestParam String taskName , String description){
////        System.out.println("------------------");
////        System.out.println("New Task ");
////        System.out.println("------------------");
////        TaskList taskList = taskListRepository.getById((long)1);
////        toDoRepository.save(new ToDo(taskName,description,taskList));
////        return new RedirectView("/");
////    }
//
//}
