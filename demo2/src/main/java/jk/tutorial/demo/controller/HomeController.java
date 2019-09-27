package jk.tutorial.demo.controller;

import java.util.ArrayList;
import java.util.List;
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

      @RequestMapping("main")
      public ModelAndView main(){
         ModelAndView mv = new ModelAndView();
         mv.setViewName("redirect:/board/list?pageNum=1");
         return mv;
      }

      //board lists
      @RequestMapping("list")
      public ModelAndView list(int pageNum, String value){
         ModelAndView mv = new ModelAndView();
         
         int endPage;
         int startPage;
         System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ");
         System.out.println("value: " + value);
         System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% " + String.valueOf(pageNum));
         if (pageNum == 0){
            pageNum = numbering(value);
            endPage = 10;
            startPage = pageNum*10 -10;  
            
            mv.addObject("queryAll", bService.selectAllBoard(startPage, endPage, value));
            return mv;
         }
         // if(value != null){
         //    pageNum =numbering(value);
         //    endPage = 10;
         //    startPage = pageNum*10 -10;
         //    mv.addObject("queryAll", bService.selectAllBoard(startPage, endPage, value));
         //    return mv;
         // }
         endPage = 10;
         startPage = pageNum*10 -10;   
         
         mv.addObject("queryAll", bService.selectAllBoard(startPage, endPage, String.valueOf(value)));
         //get number to handel number of page by assigning total page number
         int pageNumber = bService.getBoardListCnt(value);
         List<Integer> pn = new ArrayList<Integer>();
         if (pageNumber % 10 == 0) {
            System.out.println("딱 10 page");
            for(int i=1; i<pageNumber/10 +1; i++){
               pn.add(i);
            }
         } else {
            System.out.println("page가 하나 더");
            for(int i=1; i<pageNumber/10 +2; i++){
               pn.add(i);
            }
         }
         mv.addObject("pn", pn);
         // if ( value != null ){
         //    mv.addObject("searchedList", bService.selectAllBoard(startPage,endPage,value));
         //    return mv;
         // }
         
         System.out.println("총 글 목록 수: " + pageNumber);
         System.out.println("각 페이지 넘버링: " +  pn);
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
   //   @RequestMapping("search")
   //   public ModelAndView search(String value) {
   //      ModelAndView mv = new ModelAndView();
   //      int totalPageCount = numbering(value);
   //      System.out.println("검색된 결과 수:" +totalPageCount);
        
        
   //      mv.setViewName("redirect:/board/list");
   //      return mv;
   //   }

     //page numbering
     @RequestMapping
     public int numbering(String value){
        int totalPageCount =  bService.getBoardListCnt(value);
        return totalPageCount;
     }
}