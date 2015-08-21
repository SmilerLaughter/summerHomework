package domain;

public class Role {

	private int roleId;
	private String roleName;
	private String roleNote;

	public Role(String roleName, String roleNote) {
		super();

		this.roleName = roleName;
		this.roleNote = roleNote;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleNote() {
		return roleNote;
	}

	public void setRoleNote(String roleNote) {
		this.roleNote = roleNote;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

}