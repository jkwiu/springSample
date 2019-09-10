package jk.tutorial.demo.controller;

// import java.util.List;

// import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndView;

import jk.tutorial.demo.dao.UserMapper;
import jk.tutorial.demo.dto.UserDto;

@Controller
public class HomeController {

    @Autowired
	private UserMapper uMapper;

    /**
     * JSTL
     */
    // @RequestMapping(value="/")
    // public String rootPage(){
    //     System.out.println("This is root page!!sys");
    //     return "index";
    // }

    // @RequestMapping("/insert")
    // public String inputData(String name, String email){
        
    //     UserDto userDto = new UserDto();
        
    //     try {
    //         userDto.setName(name);
    //         userDto.setEmail(email);
    //         uMapper.insertUser(userDto);
    //     } catch (Exception e) {
    //         return "error";
    //     }
    //     return "success";
    // }

    // @RequestMapping("/query")
    // public String queryData(String name, HttpServletRequest request) {

    //     try{
    //         request.setAttribute("one", uMapper.selectOneUser(name));
    //     } catch (Exception e){
    //         return "error";
    //     }
    //     return "success";
    // }

    // @RequestMapping("/queryAll")
    // public String queryAllData(HttpServletRequest request) {
    //     try{
    //         System.out.println(uMapper.selectAllUser());
    //         request.setAttribute("lim", uMapper.selectAllUser());
    //     } catch (Exception e){
    //         return "error";
    //     }
    //     return "success";
    // }

    /**
     * ModelAndView
     */

     @RequestMapping("/")
     public ModelAndView mainPage(){
         ModelAndView mv = new ModelAndView();
         try {
            mv.setViewName("index");    
         } catch (Exception e) {
             mv.setViewName("error");
         }
        
         return mv;
     }

     @RequestMapping("/input")
     public ModelAndView input(String name, String email){

        ModelAndView mv = new ModelAndView();
        try {
            UserDto user = new UserDto();
            user.setName(name);
            user.setEmail(email);
            uMapper.insertUser(user);
            System.out.println("NAME: " + name + ", EMAIL: " + email);
            System.out.println("###############  Stored to DB success!!  ###############");
            mv.setViewName("success");
        } catch (Exception e){
            System.out.println("error");
        }
        return mv;
     }

     @RequestMapping("query")
     public ModelAndView query(String name) {

        ModelAndView mv = new ModelAndView();

        try {
            mv.addObject("who", uMapper.selectOneUser(name).getName());
            mv.addObject("whoseMail", uMapper.selectOneUser(name).getEmail());
            System.out.println("NAME:"+ name + ", EMAIL: " + uMapper.selectOneUser(name).getEmail());
            System.out.println("###############  Query Success!!  ###############");
            mv.setViewName("success");
        } catch (Exception e) {
            mv.setViewName("error");
        }
        return mv;
     }

     @RequestMapping("queryAll")
     public ModelAndView queryAll() {
         
        ModelAndView mv = new ModelAndView();

        try {
            mv.addObject("who", uMapper.selectAllUser());
            uMapper.selectAllUser().forEach(System.out::println);
            System.out.println("###############  QueryAll Success!!  ###############");
            mv.setViewName("success");
            return mv;
        } catch (Exception e) {
            mv.setViewName("error");
        }

        return mv;
     }
}