package jk.tutorial.demo.dao;

import java.util.List;
 
// import org.apache.ibatis.annotations.Mapper;
 
import jk.tutorial.demo.dto.BoardDTO;
import jk.tutorial.demo.service.Paging;
 
public interface BoardMapper {
    public void insertBoard (BoardDTO board);
    public void updateBoard (BoardDTO board);
    public void deleteBoard (int num);
    public void updateHitCnt (int num);
    public BoardDTO selectOneBoard (int num);
    public List<BoardDTO> selectAllBoard(Paging paging);
    public List<BoardDTO> search(String word);
    public int getBoardListCnt();   
}
