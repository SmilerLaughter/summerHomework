package daoimpl;

import java.util.List;

import dao.BaseDao;
import dao.PermissionDao;
import domain.Page;
import domain.Permission;

public class PermissionDaoImpl extends BaseDao<Permission> implements PermissionDao{

	@Override
	public void insert(Permission permission) {

		String sql = "insert into permissions(permissionName,permissionNote,method) values (?,?,?)";
		insert(sql, permission.getPermissionName(),permission.getPermissionNote(),permission.getMethod());
	}

	@Override
	public void delete(int id) {

		String sql = "delete from permissions where permissionId = ?";
		update(sql, id);
	}

	@Override
	public Permission getPermission(int id) {
		String sql = "select * from permissions where permissionId =?";
		
		return query(sql, id);
	}

	@Override
	public List<Permission> getPermissions() {
		String sql = "select * from permissions";
		return queryForList(sql);
	}

	@Override
	public long getCountWithName(String name) {
		String sql = "select count(permissionName) from permissions where permissionName = ?";
		
		return getForSingleValue(sql, name);
	}

	@Override
	public void updatePermission(Permission permission, int id) {

		String sql = "update permissions set permissionName = ? ,permissionNote = ?, method = ? where permissionId = ?";
		update(sql, permission.getPermissionName(),permission.getPermissionNote(), permission.getMethod(),id);
	}
	
	
	public long getTotalPermission(){
		String sql = "select count(permissionName) from permissions";
		return getForSingleValue(sql);
	}
	
	public List<Permission> getPageList(Page page){
		String sql = "select * from permissions limit ?,?";
		return  queryForList(sql, (page.getPageNo()-1)*page.getPageSize(),page.getPageSize());
	}
	
	public Page<Permission> getPage(int pageNo){
		
		Page<Permission> page = new Page<Permission>(pageNo);
		page.setTotleContent(getTotalPermission());
		page.setTotalPageNumber(page.getTotalPageNumber());
		page.setPageList(getPageList(page));
		
		return page;
	}

}
