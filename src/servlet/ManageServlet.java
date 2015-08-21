package servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ArticleService;
import service.ColService;
import service.PermissionService;
import service.RoleService;
import service.UserService;
import daoimpl.Article;
import domain.Col;
import domain.Page;
import domain.Permission;
import domain.Role;
import domain.User;

/**
 * Servlet implementation class ManageServlet
 */
@WebServlet("/manageServlet")
public class ManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String methodName = request.getParameter("method");
		try {
			Method method = getClass().getMethod(methodName,
					HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	private UserService userService = new UserService();
	private PermissionService permissionService = new PermissionService();

	public void deletePermission(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String permissionIdStr = request.getParameter("permissionId");
		String pageNoString = request.getParameter("pageNo");
		int pageNo = 1;
		int permissionId = -1;
		boolean flag = false;

		try {
			permissionId = Integer.parseInt(permissionIdStr);
			pageNo = Integer.parseInt(pageNoString);
			if (permissionId > 0) {
				permissionService.deletePermission(permissionId);
				request.setAttribute("succe", "删除成功");
				Page<Permission> page = permissionService.getPage(pageNo);
				request.setAttribute("page", page);
				request.getRequestDispatcher("/WEB-INF/pages/queryForPermissions.jsp").forward(request, response);
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		response.sendRedirect("error.jsp");

	}

	public void goToAddPermission(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/pages/addPermission.jsp")
				.forward(request, response);

	}

	public void addPermission(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String permissionName = request.getParameter("permissionName");
		String permissionNote = request.getParameter("permissionNote");
		String method = request.getParameter("method");
	
		Permission permission = new Permission(permissionName, permissionNote, method);
		
		StringBuffer error = new StringBuffer("");

		error = permissionService.addPermission(request, permission);

		if (error.toString().equals("")) {// 没有重复的，可以添加权限
			permissionService.addPermission(permission);
			response.sendRedirect("succe.jsp");

			return;
		}
		request.setAttribute("error", error);
		request.getRequestDispatcher("/WEB-INF/pages/addPermission.jsp")
				.forward(request, response);
		return;
	}

	public void queryForPermission(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pageNo = 1;
		String pageNoString = request.getParameter("pageNo");

		try {
			pageNo = Integer.parseInt(pageNoString);
		} catch (Exception e) {
			// e.printStackTrace();
		}

		Page<Permission> page = permissionService.getPage(pageNo);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/WEB-INF/pages/queryForPermissions.jsp")
				.forward(request, response);

	}

	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		StringBuffer errors = new StringBuffer("");
		String userId = request.getParameter("userId");
		String password = request.getParameter("userPassword");
		int id = -1;
		try {
			id = Integer.parseInt(userId);
			if (id > 0) {
				User user = userService.getUser(id);
				errors = userService.validateUserId(id);
				if (errors.toString().equals("")) {// 用戶存在
					errors = userService.validateUserPassword(id, password);
					if (errors.toString().equals("")) {// 通过验证
						session.setAttribute("user", user);
						
						Map<Integer, List<Permission>> navigationBar = userService.getNavigationBar(user);
						session.setAttribute("navigationBars", navigationBar);
						
//						request.getRequestDispatcher(
//								"/WEB-INF/pages/manage.jsp").forward(request,
//								response);
						
						request.getRequestDispatcher(
								"/WEB-INF/pages/welcome.jsp").forward(request,
								response);
						return;
					}
				}

				request.setAttribute("errors", errors);
				request.getRequestDispatcher("/index.jsp").forward(request,
						response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void goToUpdatePermission(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int id = -1;
		String permissionId = request.getParameter("permissionId");
		try {
			id = Integer.parseInt(permissionId);
			if (id > 0) {

				Permission permission = permissionService.getPermission(id);
				request.setAttribute("permission", permission);
				request.getRequestDispatcher("/WEB-INF/pages/updatePermission.jsp").forward(request, response);
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		response.sendRedirect("error.jsp");
	}

	public void updatePermission(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int id = -1;
		String permissionId = request.getParameter("permissionId");
		String permissionName = request.getParameter("permissionName");
		String permissionNote = request.getParameter("permissionNote");
		String method = request.getParameter("method");

		try {
			id = Integer.parseInt(permissionId);
			if (id > 0) {

				Permission permission = permissionService.updatePermission(
						request, id, permissionName, permissionNote,method);
				if (permission == null) {
					response.sendRedirect("succe.jsp");
					return;
				}
				request.setAttribute("permission", permission);
				request.getRequestDispatcher(
						"/WEB-INF/pages/updatePermission.jsp").forward(request,
						response);
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private RoleService roleService = new RoleService();

	public void goToAddRole(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Permission> permissions = roleService.getAllPermissions();
		request.setAttribute("permissions", permissions);

		request.getRequestDispatcher("/WEB-INF/pages/addRole.jsp").forward(
				request, response);
	}

	public void addRole(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String roleName = request.getParameter("roleName");
		String[] rolePermissions = request
				.getParameterValues("rolePermissions");

		String roleNote = request.getParameter("roleNote");
		StringBuffer error = new StringBuffer("");

		error = roleService.validateRoleName(request, roleName);
		if (error.toString().equals("")) {// 角色名没有重复
			roleService.addRole(roleName, roleNote, rolePermissions);
			response.sendRedirect("succe.jsp");
			return;                               
		}

		Role role = new Role(roleName, roleNote);
		request.setAttribute("role", role);
		request.setAttribute("rolePermissions", rolePermissions);
		goToAddRole(request, response);

	}
	
	public void queryForRole(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 int pageNo = 1;
		 String pageNoString  = request.getParameter("pageNo");
		 
		 try {
			pageNo = Integer.parseInt(pageNoString);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		Page<Role> page =  roleService.getPage(pageNo);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/WEB-INF/pages/queryForRoles.jsp")
		.forward(request, response);
		 
	}
	
	
	public void goToUpdateRole(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = -1;
		String roleId = request.getParameter("roleId");
		try {
			id = Integer.parseInt(roleId);
			if (id > 0) {
				Role role = roleService.getRole(id);
				List<Permission> permissions = roleService.getAllPermissions();
				request.setAttribute("permissions", permissions);
				
			    List<Permission> rolePermissions =  roleService.getRolePermissions(id);
				request.setAttribute("rolePermissions", rolePermissions);
				
				request.setAttribute("role", role);
				
				request.getRequestDispatcher("/WEB-INF/pages/updateRole.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void updateRole (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String roleName = request.getParameter("roleName");
		String roleNote = request.getParameter("roleNote");
		String roleId = request.getParameter("roleId");
		String[] rolePermission = request.getParameterValues("rolePermissions");
		
		int id = -1;
		try {
			id = Integer.parseInt(roleId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Role role = roleService.validateUpdateRole(roleName,roleNote,id);
		if(role == null){//通过
			
			roleService.update( id,roleName,roleNote,rolePermission);
			
			response.sendRedirect("succe.jsp");
			return;
		}
	
		//没通过
		request.setAttribute("role", role);
		request.setAttribute("error", "该角色已存在");
		List<Permission> permissions = roleService.getAllPermissions();
		request.setAttribute("permissions", permissions);
		
		List<Permission> rolePermissions = roleService.getRolePermissions(rolePermission);
		request.setAttribute("rolePermissions", rolePermissions);
		request.getRequestDispatcher("/WEB-INF/pages/updateRole.jsp").forward(request, response);
		
	}
	
	public void goToAddUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Role> roles =  roleService.getRoles();
		request.setAttribute("roles", roles);
		request.getRequestDispatcher("/WEB-INF/pages/addUser.jsp").forward(request, response);
	}
	
	public void addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String[] userRole = request.getParameterValues("userRole");
		
		userService.addUser(userName,userPassword,userRole);
		response.sendRedirect("succe.jsp");
	}
	
	
	public void queryForUser (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int pageNo = 1;
		String pageNoStr = request.getParameter("pageNo");
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Page<User> page = userService.getPage(pageNo);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/WEB-INF/pages/queryForUsers.jsp").forward(request, response);
	}
	
	
	public void goToUpdateUser (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Role> roles = roleService.getRoles();
		request.setAttribute("roles", roles);
		
		int userId = -1;
		String id = request.getParameter("userId");
		User user = null;
		try {
			userId = Integer.parseInt(id);
			if(userId > 0){
				user = userService.getUser(userId);
			}
		} catch (Exception e) {
		}
		
		request.setAttribute("user", user);
		
		
		List<Role> rolesOfUser = userService.getRoles(userId);
		
		request.setAttribute("rolesOfUser", rolesOfUser);
		request.getRequestDispatcher("/WEB-INF/pages/updateUser.jsp").forward(request, response);;
		
	}
	
	public void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String userId = request.getParameter("userId");
		String[] rolesOfUser = request.getParameterValues("rolesOfUser");
 		
		userService.updateUser(userName,userPassword,userId,rolesOfUser);
		response.sendRedirect("succe.jsp");
	}
	
	public void goToAddCol(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/pages/addCol.jsp").forward(request, response);
	}
	
	private ColService colService = new ColService();
	
	public void addCol(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String colName = request.getParameter("colName");
		StringBuffer error = new StringBuffer("");
		error = colService.addCol(colName);
		if(error.toString().equals("")){//通过
			response.sendRedirect("succe.jsp");
			return;
		}
		
		request.setAttribute("error", error);
		request.setAttribute("colName", colName);
		request.getRequestDispatcher("/WEB-INF/pages/addCol.jsp").forward(request, response);
	}
	
	public void handleCol(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int pageNo = 1;
		String pageNoStr = request.getParameter("pageNo");
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Page<Col> page =  colService.getPage(pageNo);
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("/WEB-INF/pages/handleCol.jsp").forward(request, response);
	}
	
	public void goToaddChildCol(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/pages/addChild.jsp").forward(request, response);
	}
	
	public void addChild(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		StringBuffer error = new StringBuffer("");
		String childColName = request.getParameter("childColName");
		
		String parentId = request.getParameter("parentIdOfChild");
		
		int parentIdOfChild = Integer.parseInt(parentId);
		error = colService.addChildCol(childColName,parentIdOfChild);
		
		if(error.toString().equals("")){
			response.sendRedirect("succe.jsp");
			return ;
		}
		
		request.setAttribute("error", error);
		request.setAttribute("childName", childColName);
		request.getRequestDispatcher("/WEB-INF/pages/addChild.jsp").forward(request, response);
	}
	
	

	
	public void displayCol(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HashMap<String, List<HashMap>> maps = colService.display();
		int level = colService.getAllLevel();
		
		request.setAttribute("cols", maps);
		request.setAttribute("allLevle", level);
		request.getRequestDispatcher("/WEB-INF/pages/disPlayCol.jsp").forward(request, response);
			
	}
	
	public void deleteCol(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String colIdString = request.getParameter("colId");
		int colId = -1;
		
		StringBuffer error = new StringBuffer("");
		
		try {
			colId = Integer.parseInt(colIdString);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String pageNoString = request.getParameter("pageNo");
		int pageNo = Integer.parseInt(pageNoString);
		Page<Col> page = null;
		
		if(colId > 0){
			System.out.println(colId);
			error = colService.delete(colId);
			if(error.toString().equals("")){
				
			    request.setAttribute("succe", "操作成功！");
			}
			
			if(pageNo > 0){
				page =	colService.getPage(pageNo);
				request.setAttribute("page", page);
			}
			
			request.setAttribute("error", error);
			request.getRequestDispatcher("/WEB-INF/pages/handleCol.jsp").forward(request, response);
		
		}
	}
	
	public void goToAddArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		List<Col> cols = colService.getColls();
		request.setAttribute("cols", cols);
		request.getRequestDispatcher("/WEB-INF/pages/addArticle.jsp").forward(request, response);
	}
	
	
	private ArticleService articleService = new ArticleService();
	public void addArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String articleName = request.getParameter("articleName");
		String content = request.getParameter("content");
		content = content.trim();
		content = content.replaceAll("\n|\r", "");
		String colIdString = request.getParameter("colId");
		
		
		
		int colId = -1;
		try {
			colId = Integer.parseInt(colIdString);
			
			if(colId > 0){
				articleService.addArticle(articleName,content,colId );
				response.sendRedirect("succe.jsp");
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void goToHandleArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String colIdString = request.getParameter("colId");
		int colId = -1;
		try {
			colId = Integer.parseInt(colIdString);
			if(colId > 0){
				List<Article> articles = articleService.getArticleWithColId(colId);
				if(articles!= null && articles.size() > 0){
					request.setAttribute("articles", articles);
					request.getRequestDispatcher("/WEB-INF/pages/handelArticle.jsp").forward(request, response);
					return;
				}
				
				if(articles.size() == 0 ){
					request.setAttribute("noFiles", "该栏目没有文章");
					String pageNoString = request.getParameter("pageNo");
					int pageNo = Integer.parseInt(pageNoString);
					Page<Col> page = colService.getPage(pageNo);
					request.setAttribute("page", page);
					request.getRequestDispatcher("/WEB-INF/pages/handleCol.jsp").forward(request, response);
					return ;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void displayArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int articleId = -1;
		String articleIdString = request.getParameter("articleId");
		try {
			articleId = Integer.parseInt(articleIdString);
			if(articleId > 0){
				Article article = articleService.getContent(articleId);
				request.setAttribute("article", article);
				request.getRequestDispatcher("/WEB-INF/pages/displayArticleContent.jsp").forward(request, response);
				return ;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void goToUpdateArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int articleId = -1;
		String articleIdString = request.getParameter("articleId");
		try {
			articleId = Integer.parseInt(articleIdString);
			if(articleId > 0){
				Article article = articleService.getContent(articleId);
				request.setAttribute("article", article);
				request.getRequestDispatcher("/WEB-INF/pages/updateArticle.jsp").forward(request, response);
				return ;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void updateArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String articleName = request.getParameter("articleName");
		String content = request.getParameter("content");
		content = content.trim();
		content = content.replaceAll("\n|\r", "");
		String articleIdString = request.getParameter("articleId");
		
		
		
		int articleId = -1;
		try {
			articleId = Integer.parseInt(articleIdString);
			
			if(articleId > 0){
				articleService.updateArticle(articleName,content,articleId );
				response.sendRedirect("succe.jsp");
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	public void deleteArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int articleId = -1;
		String articleIdString = request.getParameter("articleId");
		int colId = -1;
		String colIdString = request.getParameter("colId");
		
		try {
			articleId = Integer.parseInt(articleIdString);
			colId = Integer.parseInt(colIdString);
			if(articleId > 0){
				articleService.deleteArticle(articleId);
				List<Article> articles = articleService.getArticleWithColId(colId);
				
				if(articles!= null && articles.size() > 0){
						request.setAttribute("articles", articles);
						
						request.setAttribute("sucess", "删除成功");
						request.getRequestDispatcher("/WEB-INF/pages/handelArticle.jsp").forward(request, response);
						return ;
			}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public void goToUpdateColOfArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HashMap<String, List<HashMap>> maps = colService.display();
		int level = colService.getAllLevel();
		
		request.setAttribute("cols", maps);
		request.setAttribute("allLevle", level);
		request.getRequestDispatcher("/WEB-INF/pages/updateColOfArticle.jsp").forward(request, response);
			
	}
	
	
	public void updateColOfArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String articleColNameString = request.getParameter("articleCol");
		String articleIdString = request.getParameter("articleId");
		
		articleService.updateArticleCol(articleColNameString,articleIdString);
		response.sendRedirect("succe.jsp");
		
	}
	
	public void deleteUser (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int userId = -1;
		String usetIdStr = request.getParameter("userId");
		
		int pageNo = 1;
		String pageNoString = request.getParameter("pageNo");
		
		try {
			userId = Integer.parseInt(usetIdStr);
			pageNo = Integer.parseInt(pageNoString);
			if(userId > 0){
				userService.delete(userId);
				
				Page<User> page = userService.getPage(pageNo);
				request.setAttribute("page", page);
				request.setAttribute("succe", "删除成功");
				request.getRequestDispatcher("/WEB-INF/pages/queryForUsers.jsp").forward(request, response);
				
				return ;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void deleteRole (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int roleId = -1;
		int pageNo = 1;
		
		String roleIdString = request.getParameter("roleId");
		String pageNoString = request.getParameter("pageNo");
		
		try {
			roleId = Integer.parseInt(roleIdString);
			pageNo = Integer.parseInt(pageNoString);
			
			if(roleId > 0){
				roleService.delete(roleId);
				Page<Role> page = roleService.getPage(pageNo);
				
				request.setAttribute("page", page);
				request.setAttribute("succe", "删除成功");
				
				request.getRequestDispatcher("/WEB-INF/pages/queryForRoles.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void test(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/pages/test.jsp").forward(request, response);
	}
	
}
