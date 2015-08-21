package dao;

import java.util.List;

import domain.Col;
import domain.Page;

public interface ColDao {

	void addCol(Col col);
	void deleteCol(int colId);
	void update(Col col ,int colId);
	List<Col> queryForNextCols(int colId);
	
	long getCountWithColName(String colName);
	long getTotalCol();
	List<Col> getPageList(Page page);
	Page<Col> getPage(int pageNo);
	
	Col getCol(int colId);
	String getName(int colId);
	String getLevel(int colId);
	String getParentName(int colId);
	Col getChildCol(int colId);
	
	int getColAllLevel();
	List<Col> getCols();
	List<Col> getColsWithColLevel(int level);
	List<Col> getChildCols(int colId);
	
	int getColIdWithName(String colName);
}
