//package com.tododev.ToDoBot.model;
//
//import com.tododev.ToDoBot.repository.ApplicationUserRepository;
//import com.tododev.ToDoBot.repository.BoardListRepository;
//import com.tododev.ToDoBot.repository.SectionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.view.RedirectView;
//
//import java.security.Principal;
//
//public class testing {
//
//    @Controller
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
//    ---------------------------------------------------------------------------------------------
//
//
//    ---------------------------------------------------------------------------------------------------
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
