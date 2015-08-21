package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import dao.RoleDao;
import dao.RolePermissionDao;
import daoimpl.PermissionDaoImpl;
import daoimpl.RoleDaoImpl;
import daoimpl.RolePermissionDaoImpl;
import domain.Page;
import domain.Permission;

public class PermissionService {

	private PermissionDaoImpl permissionDaoImpl = new PermissionDaoImpl();
	private RolePermissionDao rolePermissionDaoImpl = new RolePermissionDaoImpl();
	
	public StringBuffer addPermission(HttpServletRequest request,Permission permission) {
		
		StringBuffer error = new StringBuffer("");
		long count = permissionDaoImpl.getCountWithName(permission.getPermissionName());
		
		if(count > 0 ){
			error.append("该权限已存在！");
		}
		request.setAttribute("permission", permission);
		
		return error;
		
	}

	public Page<Permission> getPage(int pageNo) {

		return  permissionDaoImpl.getPage(pageNo);
		
		
	}

	public void addPermission(Permission permission){
		permissionDaoImpl.insert(permission);
		
	}

	public void deletePermission(int permissionId) {

		permissionDaoImpl.delete(permissionId);
		rolePermissionDaoImpl.deleteRolePermissionWithPermissionId(permissionId);
	}

	public Permission getPermission(int id) {
		
		return permissionDaoImpl.getPermission(id);
	}

	public Permission updatePermission(HttpServletRequest request, int id ,String permissionName,String permissionNote, String method) throws ServletException, IOException {
		
	    long count = permissionDaoImpl.getCountWithName(permissionName);
	    String name = permissionDaoImpl.getPermission(id).getPermissionName();
	    
	    Permission permission = null;
	    if (count > 0 && !name.equals(permissionName)){
	    	request.setAttribute("error", "该权限已经存在！");
	    
	    	permission = new Permission(permissionName, permissionNote, method);
	    	permission.setPermissionId(id);
	    }else{
	    	 
	    	permissionDaoImpl.updatePermission(new Permission(permissionName, permissionNote,method),id);
	    	
	    }
	    
		return permission;
	}
	
	
	
}
