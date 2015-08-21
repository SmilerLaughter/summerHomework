package daoimpl;

import java.util.List;

import dao.BaseDao;
import dao.ColDao;
import domain.Col;
import domain.Page;

public class ColDaoImpl extends BaseDao<Col> implements ColDao{

	@Override
	public void addCol(Col col) {

		String sql = "insert into cols(colName,colLevel,parentId) values(?,?,?)";
		insert(sql, col.getColName(),col.getColLevel(),col.getParentId());
	}

	@Override
	public void deleteCol(int colId) {

		String sql = "delete from cols where colId = ?";
		update(sql, colId);
	}

	@Override
	public void update(Col col, int colId) {

		String sql = "update cols set colName = ?,colLevel = ?,parentId = ? where colId = ?";
		update(sql, col.getColName(),col.getColLevel(),col.getParentId(),colId);
	}

	@Override
	public List<Col> queryForNextCols(int colId) {
		String sql = "select * from cols  where parentId = ?";
		
		return queryForList(sql, colId);
	}

	@Override
	public long getCountWithColName(String colName) {
		
		String sql = "select count(colName) from cols where colName = ?";
		
		return getForSingleValue(sql, colName);
	}

	@Override
	public long getTotalCol() {
		String sql = "select count(colId) from cols";
		return getForSingleValue(sql);
	}

	
	public long getArticleNumOfCol(int colId){
		
		String sql = "select count(articleId) from articles where colId = ?";
		return getForSingleValue(sql, colId);
	}
	
	@Override
	public List<Col> getPageList(Page page) {
		String sql = "select * from cols limit ?,?";
		List<Col> cols = queryForList(sql, (page.getPageNo() - 1)*page.getPageSize(),page.getPageSize());
		long articleNum = 0;
		
		
		int colId = 0;
		String parentName = null;
		for(Col col : cols){
			colId = col.getColId();
			parentName = getParentName(colId);
			col.setParentName(parentName);
			col.setArticleNum(getArticleNumOfCol(colId));
		}
		return cols;
	}

	@Override
	public Page<Col> getPage(int pageNo) {
		Page<Col> page = new Page<Col>(pageNo);
		page.setTotleContent(getTotalCol());
		page.setPageList(getPageList(page));
		return page;
	}

	@Override
	public Col getCol(int colId) {

		String sql = "select * from cols where colId = ?";
		
		return query(sql, colId);
	}

	@Override
	public String getName(int colId) {
		String sql = "select colName from cols where colId = ?";
		
		return getForSingleValue(sql, colId);
	}

	@Override
	public String getLevel(int colId) {
		String sql = "select colLevel from cols where colId = ?";
		
		return getForSingleValue(sql, colId);
	}

	@Override
	public String getParentName(int colId) {

		String sql = "select parentId from cols where colId = ?";
		int id = getForSingleValue(sql, colId);
		String sql2 = "select colName from cols where colId = ?";
		return getForSingleValue(sql2, id) ;
	}

	@Override
	public int getColAllLevel() {

		String sql = "select max(colLevel) from cols ";
		return getForSingleValue(sql);
	}

	@Override
	public List<Col> getCols() {
		String sql = "select * from cols";
		return queryForList(sql);
	}

	@Override
	public List<Col> getColsWithColLevel(int level) {
		
		String sql = "select * from cols where colLevel = ?";
		return queryForList(sql, level);
	}

	@Override
	public Col getChildCol(int colId) {
		// TODO Auto-g
		String sql = "select colId from cols where parentId = ?";
		return query(sql, colId);
	}

	@Override
	public List<Col> getChildCols(int colId) {

		String sql = "select * from cols where parentId = ?";
		return queryForList(sql, colId);
	}

	@Override
	public int getColIdWithName(String colName) {

		String sql = "select colId from cols where colName = ?";
		
		return getForSingleValue(sql, colName);
	}
	
	

}
