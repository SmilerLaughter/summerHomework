package filter;

import java.sql.Connection;



public class TransationConnection {
	//连接只能有一个来处理事务，所以采用单例模式
	private static TransationConnection instance = new TransationConnection();
	
	private  TransationConnection(){
		
	}
	
	
	public static TransationConnection getInstance(){
		
		return instance;
	}
	
	//是以ThreadLocal作为键，
	private ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	
	//绑定连接
	public void bind(Connection connection){
		threadLocal.set(connection);
	}
	
	//获取连接——以对应的THreadLocal的键来获取对应的连接对象
	public Connection getConnection(){
		return threadLocal.get();
	}
	//解除绑定
	public void remove(){
		threadLocal.remove();
	}
}
