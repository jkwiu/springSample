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
         //총 페이지 수
         int getTotalCount = bService.getBoardListCnt(null);
         if (getTotalCount % 10 == 0) {
            getTotalCount = getTotalCount/10;
         }  else {
            getTotalCount = getTotalCount/10 + 1;
         }      
         if(pageNum < 0 || pageNum > getTotalCount){
            mv.setViewName("/board/error");
         }
         int endPage;
         int startPage;
         //when page list searched
         if (pageNum == 0){
            pageNum = numbering(value);
            System.out.println("pageNum 검색 시 : " +pageNum);
            endPage = 10;
            startPage = 0;  
            mv.addObject("queryAll", bService.selectAllBoard(startPage, endPage, value));
         //load all page list
         } else {
            endPage = 10;
            startPage = pageNum*10 -10;   
            mv.addObject("queryAll", bService.selectAllBoard(startPage, endPage, null));
         }
         
         //paging nuumbering
         int pageNumber = bService.getBoardListCnt(value);
         System.out.println("검색시 밑에 page number: " + pageNumber);
         List<Integer> pn = new ArrayList<Integer>();
         if (pageNumber % 10 == 0) {
            for(int i=1; i<pageNumber/10 +1; i++){
               pn.add(i);
            }
         } else {
            for(int i=1; i<pageNumber/10 +2; i++){
               pn.add(i);
            }
         }
         mv.addObject("pn", pn);
         System.out.println("검색시 pn: " + pn);
         System.out.println("검색시 pageNum 밑에: " + pageNum);
         int incomingPageNumber = pageNum-1;
         int startPageNum = (incomingPageNumber/10*10);
         int lastPageNum = startPageNum + 9;
         mv.addObject("startPageNum", startPageNum);
         mv.addObject("lastPageNum", lastPageNum);
         System.out.printf("startPageNum:%s  lastPageNum: %s\n", startPageNum, lastPageNum);
         System.out.println("총 페이지 수: " + pn);

         //navigation bar
         //move to last page
         mv.addObject("lastPageNumber", getTotalCount);

         //move to previous page
         int prePage = pageNum-1;
         if (prePage <= 0){
            prePage = 1;
         } 
         mv.addObject("prePage", prePage);

         //move to next page
         int nextPage = pageNum+1;
         System.out.println("마지막 page number: " + lastPageNum);
         System.out.println("nextPage1: " + nextPage);
         if(nextPage > getTotalCount){
            nextPage = nextPage - 1;
         }
         System.out.println("nextPage2: " + nextPage);
         mv.addObject("nextPage", nextPage);

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
        mv.setViewName("/board/success");
        return mv;
     }

     //delete
     @RequestMapping("delete")
     public ModelAndView delete(int no){
        ModelAndView mv = new ModelAndView();
        bService.remove(no);
        mv.setViewName("/board/success");
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

     //page numbering
     @RequestMapping
     public int numbering(String value){
        int totalPageCount =  bService.getBoardListCnt(value);
        return totalPageCount;
     }
}