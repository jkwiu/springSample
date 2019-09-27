package jk.tutorial.demo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jk.tutorial.demo.dao.BoardMapper;
import jk.tutorial.demo.dto.BoardDTO;
// import jk.tutorial.demo.service.BoardService;
import jk.tutorial.demo.service.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DemoApplicationTests{

    @Autowired
    private BoardMapper bMapper;

    @Autowired
    private BoardService bService;

    @Test
    public void writeAction(){
        BoardDTO board = new BoardDTO();
        for (int i=24; i<100; i++){
            String string = Integer.toString(i);
            board.setTitle(string);
            board.setContent(string);
        }
        bService.write(board);
    }


}