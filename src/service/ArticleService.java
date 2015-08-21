package service;

import java.util.List;

import dao.ArticleDao;
import dao.ColDao;
import daoimpl.Article;
import daoimpl.ArticleDaoImpl;
import daoimpl.ColDaoImpl;

public class ArticleService {

	
	private ArticleDao articleDaoImpl = new ArticleDaoImpl();
	private ColDao colDaoImpl = new ColDaoImpl();
	
	public void addArticle(String articleName, String content, int colId) {
		Article article = new Article(articleName, content, colId);
		articleDaoImpl.addArticle(article);
		
	}

	public List<Article> getArticleWithColId(int colId) {

		return  articleDaoImpl.getArticlesWithColId(colId);
	}

	public Article getContent(int articleId) {

		Article article = articleDaoImpl.getArticle(articleId);
		
		return article;
	}

	public void updateArticle(String articleName, String content, int articleId) {

		Article article = articleDaoImpl.getArticle(articleId);
		int colId = article.getColId();
		article = new Article(articleName, content, colId);
		
		articleDaoImpl.updateArticle(article, articleId);
		
	}

	public void deleteArticle(int articleId) {

		articleDaoImpl.deleteArticle(articleId);
	}

	public void updateArticleCol(String articleColNameString,
			String articleIdString) {

		int colId = colDaoImpl.getColIdWithName(articleColNameString);
		int articleId = -1;
		try {
			articleId = Integer.parseInt(articleIdString);
			if(articleId > 0){
				articleDaoImpl.updateArticleCol(articleId, colId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
