package jk.tutorial.demo.dao;

import java.util.List;
 
// import org.apache.ibatis.annotations.Mapper;
 
import jk.tutorial.demo.dto.BoardDTO;
 
public interface BoardMapper {
    public void insertBoard (BoardDTO board);
    public void updateBoard (BoardDTO board);
    public void deleteBoard (int num);
    public BoardDTO selectOneBoard (int num);
    public List<BoardDTO> selectAllBoard();
}
