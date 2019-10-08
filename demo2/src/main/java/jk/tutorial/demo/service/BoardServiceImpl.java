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
        System.out.println("title writen at board: " + board.getTitle().length() + "content writen at board: " + board.getContent().length());
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
        bMapper.updateHitCnt(num);
        // System.out.println("board hit plus");
        return bMapper.selectOneBoard(num);
    }
 
    @Override
    public List<BoardDTO> selectAllBoard(int startPage, int endPage, String words) {
        // TODO Auto-generated method stub
        List<BoardDTO> result = bMapper.selectAllBoard(startPage, endPage, words);
        System.out.println("db에서 나올 때 strings: " + result);
        return result;
    }

    @Override
    public int getBoardListCnt(String value){
        return bMapper.getBoardListCnt(value);
    }

    @Override
    public void writeReply(BoardDTO board){
        bMapper.insertReplyBoard(board);
    }

    @Override
    public void replyCount(int originNo, int groupOrd){
        bMapper.updateReplyCount(originNo, groupOrd);
    }

    @Override
    public int getBoardReplyCount(int num){
        int result = bMapper.getCountGroupLayer(num);
        System.out.println("답글의 총 갯 수는?: " + result);
        return result;
    }

    @Override
    public int getBoardGroupOrd(int num){
        int result = bMapper.getGroupOrd(num);
        return result;
    }

    @Override
    public void setOriginNo(int num){
        bMapper.updateOriginNo(num);
    }

    @Override
    public int getLastBoardNo(){
        int result = bMapper.getLastNo();
        System.out.println("마지막 글의 번호는: "  + result);
        return result;
    }

}
