package jk.tutorial.demo.dto;

import java.util.Date;

public class BoardDTO{
    private int num;
    private String title;
    private String content;
    private Date regDate; 
    private int count;

    //Get Values
    public int getNo(){
        return num;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

    public int getCount(){
        return count;
    }

    public Date getRegDate(){
        return regDate;
    }

    //Set Values
    public void setNo(int num){
        this.num=num;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setContent(String content){
        this.content=content;
    }

    public void setCount(int count){
        this.count = count;
    }

    public void setRegDate(Date regDate){
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "BoardDto [No=" + num + ", title=" + title + ", content=" + content 
                + ", regDate=" + regDate + "]";
    }


}