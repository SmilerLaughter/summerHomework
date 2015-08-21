package dao;

import java.util.List;

import domain.User;

public interface UserDao {

	void addUser(User user);
	
	void delete(int id);
	
	void update(User user , int userId);
	
	List<User> getUsers();
	 
	User getUser(int id);
	
	long getCountWithName(String name);
	
	
	 
}
