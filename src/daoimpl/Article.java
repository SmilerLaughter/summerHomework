package daoimpl;

public class Article {
	
	private int articleId;
	private String articleName;
	private String content;
	private int colId;
	
	public Article(){}
	public int getArticleId() {
		return articleId;
	}
	
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	
	public String getArticleName() {
		return articleName;
	}
	
	public void setArticleName(String articName) {
		this.articleName = articName;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	public int getColId() {
		return colId;
	}
	
	public void setColId(int colId) {
		this.colId = colId;
	}
	
	public Article(String articName, String content, int colId) {
		super();
		this.articleName = articName;
		this.content = content;
		this.colId = colId;
	}
	
	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", articName=" + articleName
				+ ", content=" + content + ", colId=" + colId + "]";
	}
	
	
	
	
}
