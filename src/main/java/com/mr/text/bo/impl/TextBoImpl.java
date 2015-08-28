package com.mr.text.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mr.text.bo.TextBo;
import com.mr.text.dao.TextDao;
import com.mr.text.model.Text;

public class TextBoImpl implements TextBo{
	
	@Autowired
	TextDao textDao;
	
	@Override
	public void save(Text text) {
		textDao.save(text);
	}

	@Override
	public void update(Text text) {
		textDao.update(text);
	}

	@Override
	public void delete(Text text) {
		textDao.delete(text);
	}

	@Override
	public Text loadTextByTitle(String name) {
		return textDao.loadTextByTitle(name);
	}

	@Override
	public Text loadTextById(int id) {
		return textDao.loadTextById(id);
	}

	@Override
	public List<Text> loadAllUserTexts(String userName) {
		return textDao.loadAllUserTexts(userName);
	}

}
