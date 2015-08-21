package domain;

public class Col {
	
	private int colId;
	private String colName;
	private int colLevel;
	private int parentId;
	private String parentName;
	private long articleNum;
	
	public long getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(long articleNum) {
		this.articleNum = articleNum;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public int getColId() {
		return colId;
	}
	
	public void setColId(int colId) {
		this.colId = colId;
	}
	
	public String getColName() {
		return colName;
	}
	
	public void setColName(String colName) {
		this.colName = colName;
	}
	
	public int getColLevel() {
		return colLevel;
	}
	
	public void setColLevel(int colLevel) {
		this.colLevel = colLevel;
	}
	
	public int getParentId() {
		return parentId;
	}
	
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public Col(String colName, int colLevel, int parentId) {
		super();
		this.colName = colName;
		this.colLevel = colLevel;
		this.parentId = parentId;
	}
	
	public Col() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Col [colId=" + colId + ", colName=" + colName + ", colLevel="
				+ colLevel + ", parentId=" + parentId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colName == null) ? 0 : colName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Col other = (Col) obj;
		if (colName == null) {
			if (other.colName != null)
				return false;
		} else if (!colName.equals(other.colName))
			return false;
		return true;
	}
	
	
	
	
	
}
