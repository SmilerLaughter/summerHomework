package dao;

import java.util.List;

import domain.UserRole;

public interface UserRoleDao {

	void addUserRole(int userId,int roleId);
	void deleteWithUserId(int userId);
	void deleteWithRoleId(int roleId);
	
	List<UserRole> getRoleWithUserId(int userId);

}
