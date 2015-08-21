package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import dbutils.DBUtils;

/**
 * Servlet Filter implementation class TransationFilter
 */
@WebFilter("/*")
public class TransationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TransationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Connection connection = null;
		try {
			connection = DBUtils.getConnection();
			//取消自动提交
			connection.setAutoCommit(false);
			//绑定连接
			TransationConnection.getInstance().bind(connection);
			//把请求传给Servlet
			chain.doFilter(request, response);
			//提交事物
			connection.commit();
			
		} catch (Exception e) {
			try {
				//回滚事物
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			HttpServletResponse response2 = (HttpServletResponse) response;
			response2.sendRedirect("error.jsp");
		}finally{
			//解除绑定
			TransationConnection.getInstance().remove();
			//关闭连接
			DBUtils.release(connection);
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
