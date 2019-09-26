package jk.tutorial.demo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jk.tutorial.demo.dao.BoardMapper;
import jk.tutorial.demo.dto.BoardDTO;
// import jk.tutorial.demo.service.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DemoApplicationTests{

    @Autowired
    private BoardMapper bMapper;

    // @Autowired
    // private BoardService bService;

    @Test
    public void boardTest() {
        BoardDTO board = new BoardDTO();
        bMapper.insertBoard(board);
        System.out.println(bMapper.selectOneBoard(1));
    }

    // @Test
    // public void boardTest2() {
    //     BoardDTO board = new BoardDTO();
    //     bService.write(board);
        
    //     for (BoardDTO b : bService.getBoardList())
    //         System.out.println(b);
    // }

    // @Test
    // public void pagingHandle(){
        
    // }


}