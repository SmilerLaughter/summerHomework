package daoimpl;

import java.util.List;

import dao.ArticleDao;
import dao.BaseDao;
import domain.Page;

public class ArticleDaoImpl extends BaseDao<Article> implements ArticleDao{

	@Override
	public void addArticle(Article article) {

		String sql = "insert into articles(articleName,colId,content) values(?,?,?)";
		insert(sql, article.getArticleName(),article.getColId(),article.getContent());
	}

	@Override
	public void deleteArticle(int articleId) {

		String sql = "delete from articles where articleId = ?";
		update(sql, articleId);
	}

	@Override
	public void updateArticle(Article article, int articleId) {

		String sql = "update articles set articleName = ? , colId = ?,content = ? where articleId = ?";
		update(sql, article.getArticleName(),article.getColId(),article.getContent(),articleId);
	}


	@Override
	public List<Article> getArticles() {

		String sql = "select * from articles ";
		return queryForList(sql);
	}

	@Override
	public void deleteArticlesWithColId(int colId) {

		String sql = "delete from articles where colId = ?";
		update(sql, colId);
	}

	@Override
	public Article getArticle(int articleId) {
		String sql = "select * from articles where articleId = ?";
		return query(sql, articleId);
	}

	@Override
	public List<Article> getArticlesWithColId(int colId) {

		String sql = "select * from articles where colId = ?";
		return queryForList(sql, colId);
	}

	@Override
	public long getTotleArticleNum() {

		String sql = "select count(articleName) from articles";
		return getForSingleValue(sql);
	}

	@Override
	public List<Article> getPageList(Page page) {

		String sql = "seelct * from articles limit ?,?";
		return queryForList(sql, (page.getPageNo()-1)*page.getPageSize(),page.getPageSize());
	}

	@Override
	public Page<Article> getPage(int pageNo) {

		Page<Article> page = new Page<Article>(pageNo);
		page.setTotleContent(getTotleArticleNum());
		page.setPageList(getPageList(page));
		
		return page;
	}

	@Override
	public void updateArticleCol(int articleId, int colId) {

		String sql = "update articles set colId = ? where articleId = ?";
		update(sql, colId,articleId);
	}

}
