package com.niit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/*import org.springframework.security.core.userdetails.User;*/
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.dao.ProfilePictureDao;
import com.niit.model.ProfilePicture;
import com.niit.model.User;

@RestController
public class ProfilePictureController {
	@Autowired
	private ProfilePictureDao profilePictureDao;
	@RequestMapping(value="/uploaddp",method=RequestMethod.POST)
	
	public ResponseEntity<?> UploadDp(@RequestParam CommonsMultipartFile image,HttpSession session)
	{
		User user=(User) session.getAttribute("user");
		if(user==null)
		{
			return new ResponseEntity<Error>(HttpStatus.UNAUTHORIZED);
					
					
		}
			
		ProfilePicture profilePic=new ProfilePicture();
		profilePic.setUserName(user.getUserName());
		profilePic.setImage(image.getBytes());
		profilePictureDao.save(profilePic);
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
		
		
	}

	
	@RequestMapping(value="/getImage/{userName}", method=RequestMethod.GET)
	public @ResponseBody byte[] getProfilePic(@PathVariable String userName,HttpSession session)
	{
		User user=(User) session.getAttribute("user");
		if (user==null)
				{
			return null;
				}
		else{
			ProfilePicture profilePic=profilePictureDao.getProfilePicture(userName);
			if(profilePic==null)
			{
				return null;
			}
			else{
				return profilePic.getImage();
			}
	}
}
}
