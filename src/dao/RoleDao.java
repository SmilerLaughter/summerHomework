package dao;

import java.util.List;

import domain.Page;
import domain.Role;

public interface RoleDao  {

	void addRole(Role role);
	void delete(int id);
	void update(Role role ,int roleId);
	
	Role getRole(int id);
	
	List<Role> getRoles();
	
	long getCountWithName(String name);
	
	Page<Role> getPage(int pageNo);
	
}
