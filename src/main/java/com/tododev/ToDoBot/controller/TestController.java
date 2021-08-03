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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
        List<Section> lists=boardList.getSections();
        if(lists.size()!=0) {
            model.addAttribute("lastSec", lists.get(lists.size() - 1));
            model.addAttribute("firstSec", lists.get(0));
        }
//
//        List<Section> lists=new ArrayList<>();
//        Section todo=new Section("TO-DO",boardList);
//        Section inProgress=new Section("IN-progress",boardList);
//        Section done=new Section("DONE",boardList);
//        lists.add(todo);
//        lists.add(inProgress);
//        lists.add(done);
//
//        model.addAttribute("sectionLists",lists);

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
    @GetMapping("/deleteBoard/{id}")
    public RedirectView deleteBoard(Long boardId,@PathVariable Long id) {
        boardListRepository.deleteById(boardId);
        return new RedirectView("/myboards");
    }


    @GetMapping("/deleteSection/{id}")
    public RedirectView deleteSection(Long secId,@PathVariable Long id) {
        sectionRepository.deleteById(secId);
        return new RedirectView("/board/" + id);
    }
    @GetMapping("/deleteTask/{id}")
    public RedirectView deleteTask(Long taskId,@PathVariable Long id) {

        taskRepository.deleteById(taskId);

        return new RedirectView("/board/" + id);
    }
    @GetMapping("/moveNext/{id}")
    public RedirectView moveTask(Long taskId,@PathVariable Long id,Long sectionId) {

        Queue<Section> sections = new LinkedList<>();
       BoardList boardList=boardListRepository.getById(id);

        sections.addAll(boardList.getSections());

        Long nextSection = null;
        for(int i = 0; i < sections.size(); i++){
            if(sections.peek().getId() == sectionId){
                sections.remove();
                nextSection = sections.peek().getId();
                break;
            }
            sections.remove();
        }
        Task task=taskRepository.findById(taskId).get();
        assert nextSection != null;
        task.setSection(sectionRepository.findById(nextSection).get());
       taskRepository.save(task);
        return new RedirectView("/board/" + id);
    }

    @GetMapping("/moveBack/{id}")
    public RedirectView moveUp(Long taskId,@PathVariable Long id,Long sectionId) {
        Queue<Section> sections = new LinkedList<>();
        BoardList boardList=boardListRepository.getById(id);
        boolean bh=false;
        sections.addAll(boardList.getSections());
        Long beforeSection = null;
        for(int i = 0; i < sections.size(); i++){

            if(sections.peek().getId() == sectionId){
                break;
            }else{
                beforeSection = sections.peek().getId();
                sections.remove();

            }
        }
        Task task=taskRepository.findById(taskId).get();

       task.setSection(sectionRepository.findById(beforeSection).get());
        taskRepository.save(task);
        return new RedirectView("/board/" + id);
    }


}
