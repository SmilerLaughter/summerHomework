package dao;

import java.util.List;

import domain.RolePermission;


public interface RolePermissionDao {

	void addRolePermission(int roleId , int PermissionId);
	
	void deleteRolePermissionWithPermissionId(int permissionId);
	void deleteRolePermissionWithRoleId(int roleId);

	List<RolePermission> getRolePermissions(int id);
	
	 int getPermissionId(int rolePermissionId);
}
