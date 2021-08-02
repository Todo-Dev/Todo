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
//    <Form th:action="@{/newBoard}" method="post">
//    <input type="text" name="boardName" required/>
//    <input type="text" name="description" required/>
//    <input type="submit" value="submit"/>
//</Form>
//
//<div th:each="obj : ${board}">
//    <h3 th:text="${obj.boardName}"></h3>
//    <p th:text="${obj.description}"></p>
//    <hr/>
//    <h3>Section</h3>
//    <form th:action="@{/newSection}" method="post">
//        <input type="text" th:value="${obj.id}" name="id" hidden/>
//        <input type="text" name="title"/>
//        <input type="submit" value="submit">
//    </form>
//    <h3>Task</h3>
//    <form th:action="@{/newTask}" method="post">
//        <input type="text" name="taskName"/>
//        <input type="text" name="description"/>
//        <input type="submit" value="submit">
//    </form>
//    <div th:each="section : ${obj.getSections()} ">
//        <p th:text="${section.title}"></p>
//    </div>
//    <hr/>
//</div>
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
