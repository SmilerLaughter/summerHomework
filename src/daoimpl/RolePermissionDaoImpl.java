package daoimpl;

import java.util.List;

import dao.BaseDao;
import dao.RolePermissionDao;
import domain.Permission;
import domain.RolePermission;

public class RolePermissionDaoImpl extends BaseDao<RolePermission> implements RolePermissionDao{

	@Override
	public void addRolePermission(int roleId, int permissionId) {
		
		String sql = "insert into rolePermissions(roleId,PermissionId) values(?,?)";
		insert(sql, roleId,permissionId);
	}

	@Override
	public void deleteRolePermissionWithPermissionId(int permissionId) {
		String sql = "delete from rolePermissions where permissionId = ?";
		update(sql, permissionId);
		
	}

	@Override
	public void deleteRolePermissionWithRoleId(int roleId) {

		String sql = "delete from rolePermissions where roleId = ?";
		update(sql, roleId);
	}

	@Override
	public List<RolePermission> getRolePermissions(int id) {

		String sql = "select * from rolePermissions where roleId = ?";
		return queryForList(sql, id);
	}
	
	public int getPermissionId(int rolePermissionId){
		
		String sql = "select permissionId from rolePermissions where rolePermissionId = ?";
		return getForSingleValue(sql, rolePermissionId);
	}

}
