package dao;

import java.util.List;

import domain.Permission;

public interface PermissionDao {

	void insert(Permission permission);
	
	void delete(int id);
	
	 Permission getPermission(int id);
	 
	 List<Permission> getPermissions();
	 
	 long getCountWithName(String name);
	 
	 void updatePermission(Permission permission ,int id);
}
