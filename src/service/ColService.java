package service;



import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.ast.ArrayAllocationExpression;

import dao.ArticleDao;
import dao.ColDao;
import daoimpl.ArticleDaoImpl;
import daoimpl.ColDaoImpl;
import domain.Col;
import domain.Page;

public class ColService {

	
	private ColDao colDaoImpl = new ColDaoImpl();
	private ArticleDao articleDaoImpl = new ArticleDaoImpl();
	
	public StringBuffer addCol(String colName) {
		StringBuffer error = new StringBuffer("");
		
		long count = colDaoImpl.getCountWithColName(colName);
		if(count > 0 ){
			error.append("该栏目名已存在！");
			return error;
		}
		Col col = new Col(colName, 1, 0);
		colDaoImpl.addCol(col);
		return error;
	}
	
	
	public Page<Col> getPage(int pageNo) {
		
		Page<Col> page = colDaoImpl.getPage(pageNo);
		return page;
	
	}


	public StringBuffer addChildCol(String childColName ,int parentIdOfChild) {
		
		StringBuffer error = new StringBuffer("");
		long count = colDaoImpl.getCountWithColName(childColName);
		if(count > 0){
			error.append("该栏目名已存在");
			return error;
		}
		
		Col parentCol = colDaoImpl.getCol(parentIdOfChild);
		int level = parentCol.getColLevel();
		
		int childLevel = level + 1;
		Col childCol = new Col(childColName, childLevel, parentIdOfChild);
		colDaoImpl.addCol(childCol);
		return error;
	}
	
	
	@SuppressWarnings("unchecked")
	public HashMap<String, List<HashMap>> display(){
		
		HashMap<String, List<HashMap>> cols = new HashMap<String, List<HashMap>>();//装所有的Map，循环之后新map
		HashMap<String, List<HashMap>> map = new HashMap<String, List<HashMap>>();//装先前的Map，循环之后cols
		
		List<HashMap> listMaps = new ArrayList<HashMap>();
		List<Col> childLevelCols = new ArrayList<Col>();//每级的子栏目list
		List<Col> parentLevelCols = new ArrayList<Col>();//每级的父栏目list
		List<Col> childColOfOneParentCols = new ArrayList<Col>();//指定栏目名的子栏目
		
		int allLevel = colDaoImpl.getColAllLevel();//全部的级别
		int lastLevel = 0;//子栏目的上一级栏目级别，获取父栏目list
		
		String childMapKey = null;
		String parentName = null;
		String childName = null;
		int colId = 0;
		
		for(int currentLevel = allLevel + 1 ; currentLevel > 1 ; currentLevel --){
			
			lastLevel = currentLevel - 1;
			childLevelCols = colDaoImpl.getColsWithColLevel(currentLevel);
			parentLevelCols = colDaoImpl.getColsWithColLevel(lastLevel);
			
			if(childLevelCols == null){//最后一级子栏目为null
				for(Col col : parentLevelCols){
					parentName = col.getColName();
					cols.put(parentName, null);
					
				}
			}else{
				for(Col col : parentLevelCols){//最后一级子栏目不为null
					
					colId = col.getColId();//父栏目的Id
					parentName = col.getColName();//父栏目的名字
					childColOfOneParentCols = colDaoImpl.getChildCols(colId);//指定栏目的子栏目
					
					boolean flag = false;
					
					
					for(Col chileCol : childLevelCols){
						childName = chileCol.getColName();//指定栏目子栏目的名字
						if (childColOfOneParentCols.contains(chileCol)) {//注意重写equals方法、如果存在，则添加进list
							
							HashMap childMap = new HashMap<>();
							childMap.put(childName, map.get(childName));
							listMaps.add(childMap);
							flag = true;
						}
					}
					
					
					if( flag){
						cols.put(parentName, listMaps);
					}else {
						cols.put(parentName, null);
					}
					
					listMaps = new ArrayList<HashMap>();
				}
			}
			
			//map = (HashMap<String, List<Map>>) cols.clone();//
			map = cols;
			cols = new HashMap<String, List<HashMap>>();
			
		}
		
		return map;
	}
	
	public int getAllLevel(){
		return colDaoImpl.getColAllLevel();
	}


	public StringBuffer delete(int colId) {
		// TODO Auto-generated method stub
		StringBuffer error = new StringBuffer("");
		List<Col> childCols = colDaoImpl.getChildCols(colId);
		
		System.out.println(childCols);
		
		if(childCols.size() > 0){
			error.append("该栏目拥有子栏目，不可删除！");
			return error;
		}
		
		articleDaoImpl.deleteArticlesWithColId(colId);
		colDaoImpl.deleteCol(colId);
		return error;
	}


	public Col getCol(int colId) {
		
		return colDaoImpl.getCol(colId);
	}


	public List<Col> getColls() {
		// TODO Auto-generated method stub
		return colDaoImpl.getCols();
	}
	
	
//	public Map<String, Map> displayCol(){
//		
//		Map<String, Map> cols = new HashMap<String, Map>();
//		Map<String, Object> currentMap = new HashMap<String, Object>();
//		Map<String, Map> previousMap = new HashMap<String, Map>();
//		
//		List<Col> levelCols = new ArrayList<Col>();//同级别的栏目
//		List<Col> childLevelCols = null;//子栏目
//		
//		int colId = 0;
//		String colName = null;
//		levelCols = colDaoImpl.getColsWithColLevel(1);
//		while(levelCols != null){
//			for(Col col : levelCols){
//				colId = col.getColId();
//				colName = col.getColName();
//				childLevelCols = colDaoImpl.getChildCols(colId);
//				if(childLevelCols != null){
//					cols.put(colName, previousMap);
//				}
//			}
//			levelCols = childLevelCols;
//		}
//		return cols;
//	}——获取点开了栏目的Id数组，把所有的Id对应的子栏目传给页面
	
	
	
}
