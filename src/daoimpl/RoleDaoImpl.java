package daoimpl;

import java.util.List;

import dao.BaseDao;
import dao.RoleDao;
import domain.Page;
import domain.Permission;
import domain.Role;

public class RoleDaoImpl extends BaseDao<Role> implements RoleDao{

	@Override
	public void addRole(Role role) {

		String sql = "insert into roles(roleName,roleNote) values(?,?)";
		int roleId = (int) insert(sql, role.getRoleName(),role.getRoleNote());
		role.setRoleId(roleId);
		
	}

	@Override
	public void delete(int id) {

		String sql = "delete from roles where roleId = ?";
		update(sql, id);
	}

	@Override
	public void update(Role role,int roleId) {

		String sql = "update roles set roleName = ?,roleNote = ? where roleId = ?";
	}

	@Override
	public Role getRole(int id) {

		String sql = "select * from roles where roleId = ?";
		
		return query(sql, id);
	}

	@Override
	public List<Role> getRoles() {
		String sql = "select * from roles";
		return queryForList(sql);
	}

	@Override
	public long getCountWithName(String name) {
		String sql = "select count(roleName) from roles where roleName = ?";
		
		return getForSingleValue(sql, name);
	}
	
	
	public long getToltalRoleNumber(){
		String sql = "select count(roleName) from roles";
		return getForSingleValue(sql);
	}
	
	public List<Role> getPageList(Page page){
		String sql = "select * from roles limit ?,?";
		return queryForList(sql,( page.getPageNo()-1)*page.getPageSize(),page.getPageSize());
	}
	
	public Page getPage(int pageNo){
		Page<Role> page = new Page<Role>(pageNo);
		page.setTotleContent(getToltalRoleNumber());
		page.setTotalPageNumber(page.getTotalPageNumber());
		page.setPageList(getPageList(page));
		return page;
	}
	

}
