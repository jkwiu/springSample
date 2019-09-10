package jk.tutorial.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
// import org.springframework.test.context.junit4.SpringRunner;

import jk.tutorial.demo.dao.UserMapper;
import jk.tutorial.demo.dto.UserDto;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private UserMapper uMapper;
	
	@Test
	public void userTest(){
		UserDto user = new UserDto();
		user.setName("test");
		user.setEmail("test");

		uMapper.insertUser(user);

		System.out.println(uMapper.selectOneUser("test"));
	}
}
