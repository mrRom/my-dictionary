package com.mr.word.dao;

import java.io.IOException;
import java.util.List;

import com.mr.util.search.JQGridFilter;
import com.mr.word.model.Word;

public interface WordDao {
	void save(Word word);
	void update(Word word);
	void delete(Word word);
	Word findWord(Word word);
	Word findWordById(int id);
	List<Word> findByWordName(String wordName);
	List<Word> selectAllWords();
	List<Word> findWordbygivenCriteria(String userName, JQGridFilter filter) throws IOException;
	List<Word> findWordbygivenCriteria(JQGridFilter filter) throws IOException;
	List<Word> selectAllUserWords(String userName);
	void deleteByUsername(String username);
}
