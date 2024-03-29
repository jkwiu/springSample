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
        bMapper.updateHitCnt(num);
        // System.out.println("board hit plus");
        return bMapper.selectOneBoard(num);
    }
 
    @Override
    public List<BoardDTO> selectAllBoard(Paging paging) {
        // TODO Auto-generated method stub
        return bMapper.selectAllBoard(paging);
    }

    @Override
    public List<BoardDTO> searchBoard(String word){
        return bMapper.search(word);
    }

    // @Override
    // public List<Integer> pageNum(int totalNum){
    //     List<Integer> result = new ArrayList<Integer>();
    //     if(totalNum % 10 == 0){
    //         for(int i=0; i<totalNum / 10; i++){
    //             result.add(i);
    //         }
    //     } else {
    //         for(int i=0; i<(totalNum / 10) +1; i++){
    //             result.add(i);
    //         }
    //     }
    //     return result;
    // }

    // @Override
    // public int pageNum(int totalNum){
    //     if(totalNum % 10 == 0){
    //         return totalNum/10;
    //     }
    //     return totalNum/10 +1;
    // }
    @Override
    public int getBoardListCnt(){
        return bMapper.getBoardListCnt();
    }

}
