package jk.tutorial.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jk.tutorial.demo.dao.BoardMapper;
import jk.tutorial.demo.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {
    
    @Autowired
    private BoardMapper bMapper;
 
    @Override
    public void write(BoardDTO board) {
        // TODO Auto-generated method stub
        bMapper.insertBoard(board);
    }
 
    @Override
    public void modify(BoardDTO board) {
        // TODO Auto-generated method stub
        bMapper.updateBoard(board);
    }
 
    @Override
    public void remove(int num) {
        // TODO Auto-generated method stub
        bMapper.deleteBoard(num);
    }
 
    @Override
    public BoardDTO getBoard(int num) {
        // TODO Auto-generated method stub
        return bMapper.selectOneBoard(num);
    }
 
    @Override
    public List<BoardDTO> getBoardList() {
        // TODO Auto-generated method stub
        return bMapper.selectAllBoard();
    }
}
