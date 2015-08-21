package service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.PermissionDao;
import dao.RoleDao;
import dao.RolePermissionDao;
import dao.UserRoleDao;
import daoimpl.PermissionDaoImpl;
import daoimpl.RoleDaoImpl;
import daoimpl.RolePermissionDaoImpl;
import daoimpl.UserRoleDaoImpl;
import domain.Page;
import domain.Permission;
import domain.Role;
import domain.RolePermission;

public class RoleService {

	private RoleDao roleDaoImpl = new RoleDaoImpl();
	private PermissionDao permissionDaoImpl = new PermissionDaoImpl();
	private RolePermissionDao rolePermissionDaoImpl = new RolePermissionDaoImpl(); 
	private UserRoleDao userRoleDaoImpl = new UserRoleDaoImpl();
	
	public List<Permission> getAllPermissions(){
		
		return permissionDaoImpl.getPermissions();
	}

	public StringBuffer validateRoleName(HttpServletRequest request, String roleName) {

		long count = roleDaoImpl.getCountWithName(roleName);
		StringBuffer error = new StringBuffer("");
		if(count > 0){
			error = error.append("该角色名已存在");
			request.setAttribute( "error",error);
		}
		return error;
	}

	public void addRole(String roleName, String roleNote,
			String[] rolePermissions) {

		Role role = new Role(roleName, roleNote);
		roleDaoImpl.addRole(role);
		
		int permissionID = -1;
		
		for(String permissionId : rolePermissions){
			permissionID = Integer.parseInt(permissionId);
			rolePermissionDaoImpl.addRolePermission(role.getRoleId(), permissionID);
		}
		
		
	}

	public Page<Role> getPage(int pageNo) {
	
		return  roleDaoImpl.getPage(pageNo);
		
		
	}

	public Role getRole(int id) {

	    return roleDaoImpl.getRole(id);
	}

	public List<Permission> getRolePermissions(int id) {

		List<Permission> permissionForRole = new ArrayList<Permission>();//角色拥有的权限
		List<RolePermission> rolePermissions = rolePermissionDaoImpl.getRolePermissions(id);
		 Permission permission = null;
		for(RolePermission rolePermission : rolePermissions){
			 int permissionId = rolePermission.getPermissionId();
			 permission = permissionDaoImpl.getPermission(permissionId);
			 permissionForRole.add(permission);
		 }
		 
		return permissionForRole;
				
	}

	public Role validateUpdateRole(String roleName, String roleNote, int id) {
		Role role = null;
		
		String roleOldName = roleDaoImpl.getRole(id).getRoleName();
		long count = roleDaoImpl.getCountWithName(roleName);
		if(count > 0 && !roleOldName.equals(roleName)){//重复、不能通过
			role = new Role(roleOldName, roleNote);
			role.setRoleId(id);
		}
		
		return role;
	}

	public List<Permission> getRolePermissions(String[] rolePermissions) {
		
		List<Permission> permissionForRole = new ArrayList<Permission>();//角色修改后的权限
		 Permission permission = null;
		for(String rolePermission : rolePermissions){
			
			int permissionId = Integer.parseInt(rolePermission);
			 permission = permissionDaoImpl.getPermission(permissionId);
			 
			 permissionForRole.add(permission);
		 }
		 
		return permissionForRole;
	}
	

	public void update(int id, String roleName, String roleNote,
			String[] rolePermission) {
		
		Role role = new Role(roleName, roleNote);
		roleDaoImpl.update(role, id);//更新角色信息
		
		rolePermissionDaoImpl.deleteRolePermissionWithRoleId(id);//先删除RoleId对应的权限
		int permissionId = 1;
		for(String permission: rolePermission){
			permissionId = Integer.parseInt(permission);
			rolePermissionDaoImpl.addRolePermission(id, permissionId);//重新加入权限
		}
		
	}

	public List<Role> getRoles() {

		return roleDaoImpl.getRoles();
	}

	public void delete(int roleId) {
		
		userRoleDaoImpl.deleteWithRoleId(roleId);
		rolePermissionDaoImpl.deleteRolePermissionWithRoleId(roleId);
		roleDaoImpl.delete(roleId);
		
		
	}


}
