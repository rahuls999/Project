package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.UserDao;
import com.niit.model.User;

@Controller
public class UserController {
	
	
	
@Autowired
private UserDao userDao;

	@RequestMapping(value="/registration",method=RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestBody User user){
	try
	{
		user.setEnabled(true);
	
		
		List<User> users=userDao.getAllUsers();
		for(User u: users)
			if(u.getUserName().equals(user.getUserName())){
				//Error error =new Error(0,"User alredy exist");
				return new ResponseEntity<Error>(HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
		user.setOnline(false);
		userDao.registration(user);
		return new  ResponseEntity<Void>(HttpStatus.CREATED);
	}
		
		catch(Exception e){
			//Error error=new Error(1,"cannot register user detailos");
			return new ResponseEntity<Error>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		
	}

}
	
	//for logging
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user,HttpSession session){
		
		User validUser=userDao.login(user);
		System.out.println(validUser.getFirstName()+"********");
		if(validUser==null){
			return new ResponseEntity<User>(validUser,HttpStatus.UNAUTHORIZED);
	}
		else{
			validUser.setOnline(true);
			validUser=userDao.updateUser(validUser);
			session.setAttribute("user", validUser);
			return new ResponseEntity<User>(validUser,HttpStatus.OK);
		}
		
		}
	
	
	
	//for logout
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ResponseEntity logout(HttpSession session){
		User users=(User) session.getAttribute("user");
		if(users==null){
			return new ResponseEntity<Error>(HttpStatus.UNAUTHORIZED);
		}
		
		else{
			users.setOnline(false);
			userDao.updateUser(users);
			session.removeAttribute("user");
			session.invalidate();
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
	}
	
	
}
