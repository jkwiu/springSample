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
         int pageNumber;
         int searchedWord = 0;
         int nextNumber = pageNum;

         System.out.println("두번 째 검색 시 pagNum: " + pageNum);
         //검색어 없음
         if(numbering(value) == 0){
            mv.setViewName("/board/failSearch");
            System.out.println("---------------------------절 취 선 ----------------------------");
            return mv;
         }

         if ( value != null) {
            if ( pageNum != 0 ){
               searchedWord = pageNum-1;
            } else {
               searchedWord = pageNum;
            }
            // pageNum = 0;
         }
         //총 페이지 수
         int getTotalCount = bService.getBoardListCnt(value);
         if (getTotalCount % 10 == 0) {
            getTotalCount = getTotalCount/10;
         }  else {
            getTotalCount = getTotalCount/10 + 1;
         }      
         System.out.println("getTotalCount: " + getTotalCount);
         if(pageNum < 0 || pageNum > getTotalCount){
            mv.setViewName("/board/error");
         }
         int endPage;
         int startPage;
         //when page list searched
         if (pageNum == 0 || value != null){
            //최초 이후 검색
            if(searchedWord != 0){
               startPage = searchedWord*10;
               endPage = 10;
            } else {
               //최초에 검색 pageNum=0
               startPage = 0; 
               endPage = 10;
               pageNum=1;
               System.out.println("pageNum:  " + pageNum);
            }
            mv.addObject("queryAll", bService.selectAllBoard(startPage, endPage, value));            
         //load all page list
         } else {
            //쿼리 limit 페이징 처리 테스트2
            startPage = (pageNum-1)*10;   
            endPage = 10;
            mv.addObject("queryAll", bService.selectAllBoard(startPage, endPage, null));
         }
         
         //paging nuumbering
         pageNumber = bService.getBoardListCnt(value);
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
         // int incomingPageNumber = pageNum-1;
         // int startPageNum = (incomingPageNumber/10*10);
         int startPageNum = ((pageNum-1) / 10) * 10;
         int lastPageNum = startPageNum + 9;
         if (startPageNum < 0){
            startPageNum = 0;
         }
         System.out.printf("startPageNum: %s  lastPageNum: %s pageNum: %s \n", startPageNum, lastPageNum, pageNum);
         mv.addObject("startPageNum", startPageNum);
         mv.addObject("lastPageNum", lastPageNum);

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
         int nextPage = nextNumber + 1;
         int lastPage = getTotalCount; 
          if (nextPage == 1 ){
            nextPage = nextPage + 1;
            System.out.println("nextPage2: "  + nextPage);
         } else if ( nextPage > lastPage) {
            nextPage = pageNum;
         }
         System.out.println("getTotalCount: " + pn.size());
         System.out.println("nextPage3: "  + nextPage);
         System.out.println("lastPage: " + lastPage);
         System.out.println("startPageNum: " + startPageNum + " lastPageNum: " + lastPageNum);
         mv.addObject("nextPage", nextPage);
         mv.addObject("lastPage", lastPage);

         
         System.out.println("---------------------------절 취 선 ----------------------------");
         mv.addObject("searchWord", value);
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