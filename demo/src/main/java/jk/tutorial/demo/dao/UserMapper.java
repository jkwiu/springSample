package jk.tutorial.demo.dao;

import java.util.List;
 
// import org.apache.ibatis.annotations.Mapper;
 
import jk.tutorial.demo.dto.UserDto;
 
public interface UserMapper {
    public void insertUser (UserDto user);
    // public void updateUser (UserDto user);
    public void deleteUser (String name);
    public UserDto selectOneUser (String name);
    public List<UserDto> selectAllUser();
}
