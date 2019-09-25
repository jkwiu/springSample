package jk.tutorial.demo.controller;

import java.util.Map;

// import java.util.List;

// import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jk.tutorial.demo.dto.BoardDTO;
import jk.tutorial.demo.service.BoardServiceImpl;


@Controller
@RequestMapping("/board/")
public class HomeController {
    
    
    @Autowired
    private BoardServiceImpl bService;
   
     @RequestMapping("list")
     public ModelAndView list(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("queryAll", bService.getBoardList());
        System.out.println(bService.getBoardList());
        return mv;
     }

     @RequestMapping("detail")
     public ModelAndView query(int no) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("queryOne", bService.getBoard(no));
        return mv;
     }

     @RequestMapping("write")
     public ModelAndView write() {
        ModelAndView mv = new ModelAndView();
        return mv;
     }

     @RequestMapping("write-action")
     public ModelAndView writeAction(@RequestParam Map<String, String> param){
        ModelAndView mv = new ModelAndView();
        BoardDTO board = new BoardDTO();
        board.setTitle(param.get("title"));
        board.setContent(param.get("content"));
        bService.write(board);
        mv.setViewName("redirect:/board/list");
        // for(BoardDTO b : bService.getBoardList())
        //     System.out.println(b);
        return mv;
     }

     @RequestMapping("delete")
     public ModelAndView delete(int no){
        ModelAndView mv = new ModelAndView();
        bService.remove(no);
        mv.setViewName("redirect:/board/list");
        return mv;
     }

     @RequestMapping("modify")
     public ModelAndView modify(int no){
        ModelAndView mv = new ModelAndView();
        mv.addObject("queryOne", bService.getBoard(no));
        return mv;
     }

     @RequestMapping("modify-action")
     public ModelAndView modifyAction(@RequestParam Map<String, String> param){
        ModelAndView mv =new ModelAndView();
        BoardDTO board = new BoardDTO();
        board.setTitle(param.get("title"));
        board.setContent(param.get("content"));
        board.setNo(Integer.parseInt(param.get("no")));
        bService.modify(board);
        mv.setViewName("redirect:/board/detail?no="+param.get("no"));
        return mv;
     }

}