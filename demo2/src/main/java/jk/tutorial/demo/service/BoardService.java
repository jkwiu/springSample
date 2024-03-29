package jk.tutorial.demo.service;

import java.util.List;

import jk.tutorial.demo.dto.BoardDTO;

public interface BoardService{
    public void write(BoardDTO board);
    public void modify(BoardDTO board);
    public void remove(int num);
    public BoardDTO getBoard(int num);
    public List<BoardDTO>  selectAllBoard(Paging paging);
    public List<BoardDTO> searchBoard(String word);
    // public int pageNum(int num);
    public int getBoardListCnt();
}