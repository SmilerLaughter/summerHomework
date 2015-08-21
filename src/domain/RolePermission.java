package domain;

public class RolePermission {

	private int rolePermissionId;
	private int roleId;
	private int permissionId;
	
	
	public RolePermission(int roleId, int permissionId) {
		super();
		this.roleId = roleId;
		this.permissionId = permissionId;
	}
	
	public RolePermission() {
		// TODO Auto-generated constructor stub
	}

	public int getRolePermissionId() {
		return rolePermissionId;
	}

	public void setRolePermissionId(int rolePermissionId) {
		this.rolePermissionId = rolePermissionId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

	@Override
	public String toString() {
		return "RolePermission [rolePermissionId=" + rolePermissionId
				+ ", roleId=" + roleId + ", permissionId=" + permissionId + "]";
	}
	
	
	
	
}
