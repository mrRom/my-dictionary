package com.mr.text.dao;

import java.util.List;

import com.mr.text.model.Text;

public interface TextDao {
	void save(Text text);
	void update(Text text);
	void delete(Text text);
	Text loadTextByTitle(String name);
	Text loadTextById(int id);
	List <Text> loadAllUserTexts(String userName);
}
