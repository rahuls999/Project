package com.niit.dao;

import java.util.List;

//import javax.persistence.Query;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.niit.model.User;

@Repository("userDao")

public class UserDaoImpl implements UserDao {
	

	
@Autowired
	private SessionFactory sessionFactory;

	public void registration(User user) {
		// TODO Auto-generated method stub
		
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		session.save(user);
		tx.commit();
		session.flush();
		session.close();
		
	}


	public User login(User user) {
		// TODO Auto-generated method stub
		System.out.println(user.getUserName());
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		Query query=session.createQuery("from User where userName=? and password=? and enabled=?");
		System.out.println(query);
		System.out.println(user.getUserName() + " " + user.getPassword());
		query.setString(0, user.getUserName());
		query.setString(1, user.getPassword());
		query.setBoolean(2, true);
		User validUser=(User) query.uniqueResult();
		System.out.println(user.getUserName() + " " + user.getPassword() +"valid user");
		System.out.println(validUser);
		tx.commit();
		session.flush();
		session.close();
		
		return validUser;
	}

	
	public User updateUser(User validUser) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		session.update(validUser);
		tx.commit();
		session.flush();
		session.close();
		
		
		return validUser;
	}


	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Query query =session.createQuery("from User");
		List<User> user=query.list();
		session.flush();
		session.close();
		return user;
	}

}
