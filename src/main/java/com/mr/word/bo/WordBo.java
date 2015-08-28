package com.mr.word.bo;

import java.io.IOException;
import java.util.List;

import com.mr.util.search.JQGridFilter;
import com.mr.word.model.Word;

public interface WordBo {
	void save(Word word);
	void update(Word word);
	void delete(Word word);
	void deleteByUsername(String username);
	Word findWord(Word word);
	Word findWordById(int id);
	List<Word> findByWordName(String wordName);
	List<Word> selectAllWords();
	List<Word> findWordbygivenCriteria(String userName, JQGridFilter filter) throws IOException;
	List<Word> findWordbygivenCriteria(JQGridFilter filter) throws IOException;
	List<Word> selectAllUserWords(String userName);
}
