package jk.tutorial.demo.dto;

public class UserDto{
    private String name;
    private String email;

    //Get Values
    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    //Set Values
    public void setName(String setName){
        this.name = setName;
    }

    public void setEmail(String setEmail){
        this.email = setEmail;
    }

    @Override
    public String toString(){
        return "NAME=" + name + " " + ", EMAIL=" + email;
    }


}