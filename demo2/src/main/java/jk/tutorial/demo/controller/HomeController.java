package jk.tutorial.demo.controller;

import java.util.Map;

// import java.util.List;

// import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// import jk.tutorial.demo.dao.BoardMapper;
import jk.tutorial.demo.dto.BoardDTO;
import jk.tutorial.demo.service.BoardServiceImpl;
import jk.tutorial.demo.service.Paging;


@Controller
@RequestMapping("/board/")
public class HomeController {
    
    
    @Autowired
    private BoardServiceImpl bService;

      //board lists
      @RequestMapping("list")
      public ModelAndView list(
         @RequestParam(required = false, defaultValue = "1")int page, 
         @RequestParam(required = false,defaultValue = "1")int range){
         ModelAndView mv = new ModelAndView();
         // mv.addObject("queryAll", bService.getBoardList());
         //get number to handel number of page by assigning total page number
         // int pageNumber = bService.pageNum(bService.getBoardList().size());
         // System.out.println(bService.getBoardList().size());

         //전체 게시글 갯수
         int listCnt = bService.getBoardListCnt();

         //paging 객체 생성
         Paging paging = new Paging();
         paging.pageInfo(page, range, listCnt);

         mv.addObject("paging", paging);
         mv.addObject("boardList", bService.getBoardListCnt());
         return mv;
     }

     @RequestMapping("page")
     public ModelAndView page(int pageNum){
        ModelAndView mv = new ModelAndView();

        return mv;
     }

     //board detail view
     @RequestMapping("detail")
     public ModelAndView query(int no) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("queryOne", bService.getBoard(no));
        return mv;
     }

     //write
     @RequestMapping("write")
     public ModelAndView write() {
        ModelAndView mv = new ModelAndView();
        return mv;
     }

     //needed at write method
     //add content, title
     @RequestMapping("write-action")
     public ModelAndView writeAction(@RequestParam Map<String, String> param){
        ModelAndView mv = new ModelAndView();
        BoardDTO board = new BoardDTO();
        board.setTitle(param.get("title"));
        board.setContent(param.get("content"));
        bService.write(board);
        mv.setViewName("redirect:/board/list");
        return mv;
     }

     //delete
     @RequestMapping("delete")
     public ModelAndView delete(int no){
        ModelAndView mv = new ModelAndView();
        bService.remove(no);
        mv.setViewName("redirect:/board/list");
        return mv;
     }

     //modify
     @RequestMapping("modify")
     public ModelAndView modify(int no){
        ModelAndView mv = new ModelAndView();
        mv.addObject("queryOne", bService.getBoard(no));
        return mv;
     }

     //needed at modify method
     //modify title, content by numbering serch 'no'
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

     //search
     @RequestMapping("search")
     public ModelAndView search(String word) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("searchedList", bService.searchBoard(word));
      //   System.out.println(bService.searchBoard(word));
        return mv;
     }
}