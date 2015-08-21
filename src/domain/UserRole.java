package domain;

public class UserRole {

		private int userRoleId;
		private int userId;
		private int roleId;
		
		
		public int getUserRoleId() {
			return userRoleId;
		}
		
		public void setUserRoleId(int userRoleId) {
			this.userRoleId = userRoleId;
		}
		
		public int getUserId() {
			return userId;
		}
		
		public void setUserId(int userId) {
			this.userId = userId;
		}
		
		public int getRoleId() {
			return roleId;
		}
		
		public void setRoleId(int roleId) {
			this.roleId = roleId;
		}
		
		public UserRole(int userId, int roleId) {
			super();
			this.userId = userId;
			this.roleId = roleId;
		}
		
		public UserRole() {
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "UserRoleDao [userRoleId=" + userRoleId + ", userId=" + userId
					+ ", roleId=" + roleId + "]";
		}
	}



