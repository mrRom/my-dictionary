package com.mr.word.bo.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mr.util.search.JQGridFilter;
import com.mr.word.bo.WordBo;
import com.mr.word.dao.WordDao;
import com.mr.word.model.Word;

@Service("wordBo")
public class WordBoImpl implements WordBo{
	
	@Autowired
	WordDao wordDao;
	
	public void setWordDao(WordDao wordDao) {
		this.wordDao = wordDao;
	}
	
	@Override
	public void save(Word word) {
		wordDao.save(word);
	}

	@Override
	public void update(Word word) {
		wordDao.update(word);	
	}

	@Override
	public void delete(Word word) {
		wordDao.delete(word);
	}

	@Override
	public List<Word> findByWordName(String wordName) {
		return wordDao.findByWordName(wordName); 
	}

	@Override
	public List<Word> selectAllWords() {
		return wordDao.selectAllWords();
	}

	@Override
	public Word findWord(Word word) {
		return wordDao.findWord(word);
	}

	@Override
	public Word findWordById(int id) {
		return wordDao.findWordById(id);
	}
	
	@Override
	public List<Word> findWordbygivenCriteria(String userName, JQGridFilter filter) throws IOException{
		return wordDao.findWordbygivenCriteria(userName, filter);
	}
	
	@Override
	public List<Word> findWordbygivenCriteria(JQGridFilter filter) throws IOException{
		return wordDao.findWordbygivenCriteria(filter);
	}

	@Override
	public List<Word> selectAllUserWords(String userName) {
		return wordDao.selectAllUserWords(userName);
	}

	@Override
	public void deleteByUsername(String username) {
		wordDao.deleteByUsername(username);
	}
}
