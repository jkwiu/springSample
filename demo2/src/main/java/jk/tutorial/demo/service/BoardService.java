package jk.tutorial.demo.service;

import java.util.List;

import jk.tutorial.demo.dto.BoardDTO;

public interface BoardService{
    public void write(BoardDTO board);
    public void modify(BoardDTO board);
    public void remove(int num);
    public BoardDTO getBoard(int num);
    public List<BoardDTO>  selectAllBoard(int startPage, int endPage, String words);
    // public List<BoardDTO> searchBoard(String word);
    // public int pageNum(int num);
    public int getBoardListCnt(String value);
    public void replyCount(int originNo, int groupOrd);
    public void writeReply(BoardDTO board);
    public int getBoardReplyCount(int num);
    public int getLastBoardNo();
    public int getBoardGroupOrd(int num);
    public void setOriginNo(int num);
}