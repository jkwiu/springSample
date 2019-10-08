package jk.tutorial.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
         int searchedWord = 0;  //search로 들어왔을 경우

         //검색어 없음
         // if(numbering(value) == 0){
         //    mv.setViewName("/board/failSearch");
         //    System.out.println("---------------------------절 취 선(검색 결과 없음) ----------------------------");
         //    return mv;
         // }

         //검색어가 있으면
         if ( value != null) {
            //재검색이면
            if ( pageNum != 0 ){
               searchedWord = pageNum-1;
            //최초 검색이면 searchedWord가 0을 물고 있는다
            } else {
               searchedWord = pageNum;
            }
         }
         //총 페이지 수
         int getTotalCount = bService.getBoardListCnt(value);
         if (getTotalCount % 10 == 0) {
            getTotalCount = getTotalCount/10;
         } else {
            getTotalCount = getTotalCount/10 + 1;
         }    
         // if(pageNum < 0){
         //    System.out.println("pageNum이 0보다 작습니다.");
         //    mv.setViewName("/board/error");
         // } else if (pageNum > getTotalCount){
         //    System.out.println("getTotalCount: " + getTotalCount);
         //    mv.setViewName("redirect:/board/list?pageNum=1");
         // }
         int endPage;
         int startPage;
         //when page list searched
         if (pageNum == 0 || value != null){
            //최초 이후 검색
            if(searchedWord != 0){
               startPage = (pageNum - 1) * 10;
               endPage = 10;
            } else {
               //최초에 검색 pageNum=0
               startPage = 0; 
               endPage = 10;
               pageNum=1;
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
         int startPageNum = ((pageNum-1) / 10) * 10;  //variable to query
         int lastPageNum = startPageNum + 9;          //variable to query
         if (startPageNum < 0){
            startPageNum = 0;
         }
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
         int nextPage = pageNum + 1;
         int initialPage = 1;

         if ( nextPage > getTotalCount ) {
            nextPage = pageNum;
         } else if ( getTotalCount == 1 ) {
            nextPage = 1;
         }
         mv.addObject("nextPage", nextPage);
         mv.addObject("lastPage", getTotalCount);

         System.out.println("pn: " + pn);
         System.out.println("startPage: " + startPage);
         System.out.println("endPage: " + endPage);
         System.out.println("startPageNum: " + startPageNum);
         System.out.println("lastPageNum: " + lastPageNum);
         System.out.println("nextPage: " + nextPage);
         System.out.println("initialPage: " + initialPage);
         System.out.println("pageNum: " + pageNum);
         System.out.println("searchedWord: " + searchedWord);
         System.out.println("searchWord: " + value);
         System.out.println("getTotalCount: " + getTotalCount);
         System.out.println("---------------------------절 취 선 ----------------------------");
         mv.addObject("searchWord", value);
         mv.addObject("initialPage", 1);
         mv.addObject("searchedWord", searchedWord);
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
        System.out.println("title in home controller: " + param.get("title"));
        System.out.println("content in home controller: " + param.get("content"));
        bService.write(board);
        int originNo = bService.getLastBoardNo();
      //   int groupOrd = bService.getBoardGroupOrd(originNo);
        System.out.println("[write-action] originNo는 " +   originNo);
      //   System.out.println("[write-action] groupOrd는 " + groupOrd);
        bService.setOriginNo(originNo);
        mv.setViewName("/board/success");
        return mv;
     }

     //reply
     @RequestMapping("reply")
     public ModelAndView reply(int no){
        ModelAndView mv = new ModelAndView();
        //답글의 form 부분
        int countLayer = bService.getBoardReplyCount(no);
        System.out.println("원글의 번호는: " + no);
        System.out.println("답글 수는: " +countLayer);
        String reRepeat = "re:";
        for(int i = 0; i<countLayer; i++){
           reRepeat += reRepeat;
        }
        mv.addObject("re", "re:");
        mv.addObject("queryOne", bService.getBoard(no));
        mv.addObject("reply", bService.getBoard(no).getTitle());
        return mv;
     }

     //reply-action
     @RequestMapping("reply-action")
     public ModelAndView replyAction(@RequestParam Map<String, String> param){
       ModelAndView mv = new ModelAndView();
       BoardDTO board = new BoardDTO();
       board.setTitle(param.get("title"));
       System.out.println("이 답글의 제목은: " +  param.get("title"));
       board.setContent(param.get("content"));
       int originNo = Integer.parseInt(param.get("no"));
       int groupOrd = Integer.parseInt(param.get("groupOrd"));
       System.out.println("[reply-action] originNo: " + originNo);
       System.out.println("[reply-action] groupOrd: " + groupOrd);
       bService.writeReply(board);
       bService.setOriginNo(originNo);
       bService.replyCount(originNo, groupOrd);
       mv.setViewName("redirect:/board/list?pageNum=1");
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