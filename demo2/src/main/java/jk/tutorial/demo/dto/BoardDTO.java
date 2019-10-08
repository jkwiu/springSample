package jk.tutorial.demo.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardDTO{
    private int num;
    private String title;
    private String content;
    private Date regDate; 
    private int count;
    /*
     *  답글 추가 필드
     */
    /** 원글 번호 **/
    private int originNo;
    /** 원글(답글포함)에 대한 순서 **/
    private int groupOrd;
    /** 답글 계층 **/
    private int groupLayer;

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

    public String getRegDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
        String strDate = dateFormat.format(regDate);
        System.out.println("Manufactoried regDate:  " + strDate);
        return strDate;
    }

    public int getOriginNo(){
        return originNo;
    }

    public int getGroupOrd(){
        return groupOrd;
    }

    public int getGroupLayer(){
        return groupLayer;
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

    public void setOriginNo(int originNo){
        this.originNo = originNo;
    }

    @Override
    public String toString() {
        return "BoardDto [No=" + num + ", title=" + title + ", content=" + content 
                + ", count=" + count + ", regDate=" + regDate + "]";
    }


}