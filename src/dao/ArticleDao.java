package dao;

import java.util.List;

import daoimpl.Article;
import domain.Page;

public interface ArticleDao {
	
	void addArticle(Article article);
	void deleteArticle(int articleId);
	
	void updateArticle(Article article ,int articleId);
	
	List<Article> getArticles();
	
	Article getArticle (int articleId);
	void deleteArticlesWithColId(int colId);
	
	List<Article> getArticlesWithColId(int colId);
	
	long getTotleArticleNum();
	List<Article> getPageList(Page page);
	Page<Article> getPage(int pageNo);
	
	void updateArticleCol(int articleId,int colId);
}
