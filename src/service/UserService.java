package service;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.omg.CORBA.PRIVATE_MEMBER;

import dao.PermissionDao;
import dao.RoleDao;
import dao.RolePermissionDao;
import dao.UserDao;
import dao.UserRoleDao;
import daoimpl.PermissionDaoImpl;
import daoimpl.RoleDaoImpl;
import daoimpl.RolePermissionDaoImpl;
import daoimpl.UserDaoImpl;
import daoimpl.UserRoleDaoImpl;
import domain.Page;
import domain.Permission;
import domain.Role;
import domain.RolePermission;
import domain.User;
import domain.UserRole;

public class UserService {
	
	private UserDaoImpl userDaoImpl = new UserDaoImpl();
	private UserRoleDao userRoleDaoImpl = new UserRoleDaoImpl();
	private RoleDao roleDaoImpl = new RoleDaoImpl();
	private PermissionDao permissionDaoImpl = new PermissionDaoImpl();
	private RolePermissionDao rolePermissionDaoImpl = new RolePermissionDaoImpl();
	
	public StringBuffer validateUserId ( int id){
		StringBuffer errors = new StringBuffer("");
		User user = userDaoImpl.getUser(id);
		if(user == null){
			errors.append("该用户不存在");
		}
		return errors;
	}
	
	public StringBuffer validateUserPassword(int id, String password){
		
		StringBuffer errors = new StringBuffer("");
		User user = userDaoImpl.getUser(id);
		String userPassword = user.getUserPassword();
		
		if( !userPassword.equals(password)){
			errors.append("账号和密码不匹配");
		}
		return errors;
	}
	
	
	public User getUser(int id){
		return userDaoImpl.getUser(id);
	}

	
	public void addUser(String userName, String userPassword, String[] userRole) {

		User user = new User( userName, userPassword);
		userDaoImpl.addUser(user);
		int id = 1;
		for(String roleId: userRole){
			id = Integer.parseInt(roleId);
			userRoleDaoImpl.addUserRole(user.getUserId(), id);
		}
		
	}

	public Page<User> getPage(int pageNo) {
		
		return  userDaoImpl.getPage(pageNo);
	
	}

	public List<Role> getRoles(int userId) {
		 List<UserRole> userRoles =  userRoleDaoImpl.getRoleWithUserId(userId);
		 List<Role> rolesOfUser = new ArrayList<Role>();
		 int roleID = 0;
		 Role role = null;
		 for(UserRole userRole : userRoles){
			roleID = userRole.getRoleId();
			role =  roleDaoImpl.getRole(roleID);
			rolesOfUser.add(role);
		 }
		 return rolesOfUser;
	}

	public void updateUser(String userName, String userPassword, String userId,
			String[] rolesOfUser) {
		User user = new User(userName, userPassword);
		int id = Integer.parseInt(userId);
		userDaoImpl.update(user, id);
		
		int roleId = 0;
		userRoleDaoImpl.deleteWithUserId(id);
		
		for(String roleNo : rolesOfUser){
			roleId = Integer.parseInt(roleNo);
			userRoleDaoImpl.addUserRole(id, roleId);
		}
		
	}

	public void delete(int userId) {

		userRoleDaoImpl.deleteWithUserId(userId);
		userDaoImpl.delete(userId);
	}

	public Map<Integer, List<Permission>> getNavigationBar(User user) {

		Map<Integer, List<Permission>> navigationBar = new HashMap<Integer, List<Permission>>();
		
		Set<Permission> permissions = getPermissions(user);
		List<Permission> list1 = new ArrayList<Permission>();
		List<Permission> list2 = new ArrayList<Permission>();
		List<Permission> list3 = new ArrayList<Permission>();
		List<Permission> list4 = new ArrayList<Permission>();
		List<Permission> list5 = new ArrayList<Permission>();
		
		int level = 0;
		for(Permission permission : permissions){
			level = permission.getLevel();
			if(level == 1){
				list1.add(permission);
			}else if(level == 2) {
				list2.add(permission);
			}else if (level == 3) {
				list3.add(permission);
			}else if (level == 4) {
				list4.add(permission);
			} else if (level == 5){
				list5.add(permission);
			}
		}
		
		navigationBar.put(1, list1);
		navigationBar.put(2, list2);
		navigationBar.put(3, list3);
		navigationBar.put(4, list4);
		navigationBar.put(5, list5);
		
		return navigationBar;
	}
	
	public Set<Permission> getPermissions(User user ){
		int userId = user.getUserId();
		List<UserRole> userRoles = userRoleDaoImpl.getRoleWithUserId(userId);
		List<Role> roles = new ArrayList<Role>();
		Set<Permission> permissions = new HashSet<Permission>();
		
		int roleId = 0;
		int permissionId = 0;
		
		if(userRoles != null)
			for(UserRole userRole : userRoles){
				roles.add(roleDaoImpl.getRole(userRole.getRoleId()));
			}
		
		List<RolePermission> rolePermissions  = new ArrayList<RolePermission>();
		if(roles != null && roles.size() > 0){
			for(Role role : roles){
				roleId = role.getRoleId();
				 List<RolePermission> rolePermissions2 = rolePermissionDaoImpl.getRolePermissions(roleId);
				 rolePermissions.addAll(rolePermissions2);
			}
		}
		
		int rolePermissionId = 0;
		
		if(rolePermissions != null && rolePermissions.size() > 0){
			for(RolePermission rolePermission : rolePermissions){
				rolePermissionId = rolePermission.getRolePermissionId();
				permissionId = rolePermissionDaoImpl.getPermissionId(rolePermissionId);
				permissions.add(permissionDaoImpl.getPermission(permissionId));
			}
		}
				
		return permissions;
	}
}
