package daoimpl;

import java.util.List;

import dao.BaseDao;
import dao.UserRoleDao;
import domain.UserRole;

public class UserRoleDaoImpl extends BaseDao<UserRole> implements UserRoleDao{

	@Override
	public void addUserRole(int userId, int roleId) {
		
		String sql = "insert into userRoles(userId,roleId) values(?,?)";
		insert(sql, userId,roleId);
	}

	@Override
	public void deleteWithUserId(int userId) {
		String sql = "delete from userRoles where userId = ?";
		update(sql, userId);
		
	}

	@Override
	public void deleteWithRoleId(int roleId) {

		String sql = "delete from userRoles where roleId = ?";
		update(sql, roleId);
	}

	@Override
	public List<UserRole> getRoleWithUserId(int userId) {
		String sql = "select * from userRoles where userId = ?";
		return queryForList(sql, userId);
	}
	

}
