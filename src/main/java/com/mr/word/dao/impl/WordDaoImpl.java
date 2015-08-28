package com.mr.word.dao.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.mr.util.CustomHibernateDaoSupport;
import com.mr.util.search.JQGridFilter;
import com.mr.util.search.Rules;
import com.mr.word.dao.WordDao;
import com.mr.word.model.Word;

@Repository("wordDao")
public class WordDaoImpl extends CustomHibernateDaoSupport implements WordDao{

	@Override
	public void save(Word word) {
		getHibernateTemplate().save(word);	
	}

	@Override
	public void update(Word word) {
		getHibernateTemplate().update(word);
	}

	@Override
	public void delete(Word word) {
		getHibernateTemplate().delete(word);	
	}

	@Override
	public List<Word> findByWordName(String wordName) {
		@SuppressWarnings("unchecked")
		List<Word> list = getHibernateTemplate().find(
                "from Word where wordName=?", wordName
           );
	return list;
	}
	
	public List<Word> selectAllWords(){
		@SuppressWarnings("unchecked")
		List<Word> list = getHibernateTemplate().find(
                     "from Word order by wordName");
		return list;
	}

	@Override
	public Word findWord(Word word) {
		Word result = (Word)getHibernateTemplate().find(
                "from Word where wordName=? and wordTranslation=? and usageExample = ?", word.getWordName(), word.getWordTranslation(), word.getUsageExample() 
           ).get(0);
		return result;
	}

	@Override
	public Word findWordById(int id) {
		Word result = (Word)getHibernateTemplate().find(
                "from Word where wordId = ?", id 
           ).get(0);
		return result;
	}
	
	@Override
	public List<Word> findWordbygivenCriteria(JQGridFilter filter) throws IOException{
		StringBuilder sql = new StringBuilder("SELECT * FROM word WHERE ");
		Iterator<Rules> itr = filter.getRules().iterator(); 
		while(itr.hasNext()) {
			Rules rules = itr.next();
			String field = "";
			String compareOption = "= ";
			if (rules.getField().equalsIgnoreCase("wordName")){
				field = "word_name ";
			} else if (rules.getField().equalsIgnoreCase("wordTranslation")){
				field = "word_translation ";
			} else if (rules.getField().equalsIgnoreCase("usageExample")){
				field = "usage_example ";
			} else {
				throw new IOException("Cann't proccess the rules.field!");
			}
			//sql.append(field);
			if(rules.getOp().equalsIgnoreCase("eq")){
				compareOption = "= "; 
				//sql.append("= ");
				//sql.append("'" + rules.getData() +"' ");
			} else if(rules.getOp().equalsIgnoreCase("cn")){
				compareOption = "LIKE ";
				//sql.append("LIKE ");
				//sql.append("'%" + rules.getData() +"%' ");
			} else{
				throw new IOException("Cann't proccess the rules.option!");
			}
			sql.append(field + compareOption + " :" + rules.getField() + " ");
			if (itr.hasNext()){
				if(filter.getGroupOp().equalsIgnoreCase("AND")){
					sql.append("AND ");
				} else if(filter.getGroupOp().equalsIgnoreCase("OR")){
					sql.append("OR ");
				} else{
					throw new IOException("Cann't proccess the GroupOp");
				}
			} else {
				sql.append(";");
			}
		}
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addEntity(Word.class);
		for (Rules rules: filter.getRules()){
			if (rules.getOp().equalsIgnoreCase("eq")){
				query.setParameter(rules.getField(), rules.getData());
			} else {
				query.setParameter(rules.getField(), "%"+rules.getData()+"%");
			} 
		}
		@SuppressWarnings("unchecked")
		List<Word> list = query.list(); 
		return list;
	}
	@Override
	public List<Word> findWordbygivenCriteria(String userName, JQGridFilter filter) throws IOException{
		StringBuilder sql = new StringBuilder("SELECT * FROM word WHERE ");
		Iterator<Rules> itr = filter.getRules().iterator(); 
		while(itr.hasNext()) {
			Rules rules = itr.next();
			String field = "";
			String compareOption = "= ";
			if (rules.getField().equalsIgnoreCase("wordName")){
				field = "word_name ";
			} else if (rules.getField().equalsIgnoreCase("wordTranslation")){
				field = "word_translation ";
			} else if (rules.getField().equalsIgnoreCase("usageExample")){
				field = "usage_example ";
			} else {
				throw new IOException("Cann't proccess the rules.field!");
			}
			//sql.append(field);
			if(rules.getOp().equalsIgnoreCase("eq")){
				compareOption = "= "; 
				//sql.append("= ");
				//sql.append("'" + rules.getData() +"' ");
			} else if(rules.getOp().equalsIgnoreCase("cn")){
				compareOption = "LIKE ";
				//sql.append("LIKE ");
				//sql.append("'%" + rules.getData() +"%' ");
			} else{
				throw new IOException("Cann't proccess the rules.option!");
			}
			sql.append(field + compareOption + " :" + rules.getField() + " ");
			if (itr.hasNext()){
				if(filter.getGroupOp().equalsIgnoreCase("AND")){
					sql.append("AND ");
				} else if(filter.getGroupOp().equalsIgnoreCase("OR")){
					sql.append("OR ");
				} else{
					throw new IOException("Cann't proccess the GroupOp");
				}
			} else {
				sql.append("AND d_user_name = :user;");
			}
		}
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addEntity(Word.class);
		for (Rules rules: filter.getRules()){
			if (rules.getOp().equalsIgnoreCase("eq")){
				query.setParameter(rules.getField(), rules.getData());
			} else {
				query.setParameter(rules.getField(), "%"+rules.getData()+"%");
			} 
		}
		query.setParameter("user", userName);
		@SuppressWarnings("unchecked")
		List<Word> list = query.list(); 
		return list;
	}

	@Override
	public List<Word> selectAllUserWords(String userName) {
		@SuppressWarnings("unchecked")
		List<Word> list = getHibernateTemplate().find(
                     "from Word where userName = ? order by wordName", userName);
		return list;
	}

	@Override
	public void deleteByUsername(String userName) {
		SQLQuery query = getSession().createSQLQuery("DELETE FROM word WHERE d_user_name = :userName");
		query.setParameter("userName", userName);
				query.executeUpdate();
	}
}
