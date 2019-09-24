package jk.tutorial.demo.controller;

// import java.util.List;

// import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jk.tutorial.demo.dao.BoardMapper;
import jk.tutorial.demo.dto.BoardDTO;
import jk.tutorial.demo.service.BoardServiceImpl;


@Controller
public class HomeController {

    @Autowired
    private BoardMapper bMapper;
    
    @Autowired
    private BoardServiceImpl bService;
   
     @RequestMapping("/list")
     public ModelAndView list(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("list");
        return mv;
     }

     @RequestMapping("/query")
     public ModelAndView query(int no){
       ModelAndView mv = new ModelAndView();

        return mv;
     }

     @RequestMapping("/write")
     public ModelAndView write() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("write");
        return mv;
     }

     @RequestMapping("write-action")
     public ModelAndView writeAction(String title, String content){
         ModelAndView mv = new ModelAndView();
         BoardDTO board = new BoardDTO();
         board.setTitle(title);
         board.setContent(content);
         bService.write(board);
         mv.setViewName("list");
         for(BoardDTO b : bService.getBoardList())
            System.out.println(b);
        return mv;
     }
}