package dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mysql.jdbc.Statement;
import com.sun.org.apache.bcel.internal.generic.NEW;

import daoimpl.Article;
import dbutils.DBUtils;
import filter.TransationConnection;

public class BaseDao<T> implements Dao<T> {
	private QueryRunner queryRunner = new QueryRunner();
	Class clazz = null;
	
	 public BaseDao() {
		 
		Type type =	getClass().getGenericSuperclass();//返回其父类：Dao
		if(type instanceof ParameterizedType){//判断是否是类Parameterized的实例,ParameterizedType就是代表是否是带泛型参数的类
			Type[] types = ((ParameterizedType)type).getActualTypeArguments();//返回Dao的泛型参数数组
			if(types != null && types.length > 0){
				clazz = (Class)types[0];//需要实例化的clazz就是第一个参数类型
			}
		}
			
	 
	 }

	@Override
	public long insert(String sql, Object... args) {
		long id = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = TransationConnection.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//返回主键值
			
			if(args != null && args.length > 0){
				for(int i = 0; i < args.length ; i++){
					preparedStatement.setObject(i + 1, args[i]);
				}
			}
			
			preparedStatement.execute();
			resultSet = preparedStatement.getGeneratedKeys();//获得主键值
			
			if(resultSet.next() ){
				id = resultSet.getLong(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtils.release(preparedStatement, resultSet);
		}
		return id;
	}

	@Override
	public void update(String sql, Object... args) {
		Connection connection = null;
		try {
			connection = TransationConnection.getInstance().getConnection();
			queryRunner.update(connection, sql, args);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}


	@Override
	public T query(String sql, Object... args) {

		Connection connection = null;
		try {
			connection = TransationConnection.getInstance().getConnection();
			return queryRunner.query(connection, sql, new BeanHandler<T>(clazz), args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<T> queryForList(String sql, Object... args) {
		Connection connection = null;
		try {
			connection = TransationConnection.getInstance().getConnection();
			return queryRunner.query(connection, sql, new BeanListHandler<T>(clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <V> V getForSingleValue(String sql, Object... args) {
		Connection connection = null;
		try {
			connection = TransationConnection.getInstance().getConnection();
			return (V) queryRunner.query(connection, sql, new ScalarHandler(), args);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public void batch(String sql, Object[]... args) {
		Connection connection = null;
		try {
			connection = TransationConnection.getInstance().getConnection();
			queryRunner.batch(connection, sql, args);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ;
		
	}
	

}
