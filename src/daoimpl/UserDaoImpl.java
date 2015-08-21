package daoimpl;

import java.util.List;

import dao.BaseDao;
import dao.UserDao;
import domain.Page;
import domain.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao{

	@Override
	public void addUser(User user) {

		String sql = "insert into users(userName,userpassword) values(?,?)";
		int userId =(int)insert(sql, user.getUserName(),user.getUserPassword());
		user.setUserId(userId);
	}

	@Override
	public void delete(int id) {
		String sql = "delete from users where userId = ?";
		update(sql, id);
		
	}

	@Override
	public void update(User user ,int userId) {

		String sql = "update users set userName = ?,userPassword = ? where id = ?";
		update(user.getUserName(),user.getUserPassword(),userId);
	}

	@Override
	public List<User> getUsers() {

		String sql = "select * from users";
		return queryForList(sql);
	
	}

	@Override
	public User getUser(int id) {
		String sql = "select * from users where userId = ?";
		
		return query(sql, id);
	}

	@Override
	public long getCountWithName(String name) {
		String sql = "select count(userName) from user where userName = ?";
		
		return getForSingleValue(sql, name);
	}
	
	public long getTotalUser(){
		String sql = "select count(userId) from users";
		return getForSingleValue(sql);
	}
	
	public List<User> getPageList(Page page){
		String sql = "select * from users limit ?,?";
		return queryForList(sql, (page.getPageNo()-1)*page.getPageSize(),page.getPageSize());
	}
	
	public Page<User> getPage(int pageNo){
		
		Page<User> page = new Page<User>(pageNo);
		page.setTotleContent(getTotalUser());
		page.setTotalPageNumber(page.getTotalPageNumber());
		page.setPageList(getPageList(page));
		
		return page;
	}

}
