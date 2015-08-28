package com.mr.text.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mr.text.dao.TextDao;
import com.mr.text.model.Text;
import com.mr.util.CustomHibernateDaoSupport;

@Repository("textDao")
public class TextDaoImpl extends CustomHibernateDaoSupport implements TextDao{

	@Override
	public void save(Text text) {
		getHibernateTemplate().save(text);
	}

	@Override
	public void update(Text text) {
		getHibernateTemplate().update(text);	
	}

	@Override
	public void delete(Text text) {
		getHibernateTemplate().delete(text);
	}

	@Override
	public List<Text> loadAllUserTexts(String userName) {
		@SuppressWarnings("unchecked")
		List<Text> list = getHibernateTemplate().find(
                     "from Text where userName = ? order by title", userName);
		return list;
	}

	@Override
	public Text loadTextByTitle(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Text loadTextById(int id) {
		Text result = (Text)getHibernateTemplate().find(
                "from Text where textId = ?", id 
           ).get(0); 
		return result;
	}

}
