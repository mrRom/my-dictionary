package com.mr.word;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mr.util.search.JQGridFilter;
import com.mr.word.bo.WordBo;
import com.mr.word.model.Word;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/config/BeanLocations.xml"})
public class WordBoTest {
	
	@Autowired
	WordBo wordBo;
	@Before
	@Test
	public void insertWordTest() {
		Word word = new Word();
		word.setWordName("Dog");
		word.setWordTranslation("Собака");
		word.setUsageExample("My dog is very big.");
		wordBo.save(word);
	}
	
	@Test
	public void findByWordNameTest() {
		assertEquals("Dog", wordBo.findByWordName("Dog").get(0).getWordName());
	}

	@Test
	public void updateWordTest() {
		Word word = new Word();
		word.setWordName("Dog");
		word.setWordTranslation("Собака");
		word.setUsageExample("My dog is very big.");
		Word result = wordBo.findWord(word);
		result.setWordName("Dog");
		result.setWordTranslation("Пес");
		result.setUsageExample("My dog is very smart.");
		wordBo.update(result);
		assertEquals("Dog", wordBo.findByWordName("Dog").get(0).getWordName());
		assertEquals("Пес", wordBo.findByWordName("Dog").get(0)
				.getWordTranslation());
		assertEquals("My dog is very smart.",
				wordBo.findByWordName("Dog").get(0).getUsageExample());
	}

	@Test
	public void selectAllWordsTest() {
		List<Word> list = wordBo.selectAllWords();
		assertTrue(list.size() > 0);
	}

	@Test
	public void deleteWordTest() {
		Word word = (Word) wordBo.findByWordName("Dog").get(0);
		wordBo.delete(word);
	}
	
	@Test
	public void findWordbygivenCriteria(){
		/*{"groupOp":"AND",
		 * "rules":[{"field":"wordName","op":"eq","data":"111"},
		 * {"field":"wordTranslation","op":"cn","data":"111"},
		 * {"field":"usageExample","op":"eq","data":"111"}]}
		 */
		
		JQGridFilter filter = new JQGridFilter();
		String searchFilters = "{\"groupOp\":\"AND\",\"rules\":"+
								"[{\"field\":\"wordName\",\"op\":"+
								"\"eq\",\"data\":\"Dog\"},{\"field\":"+
								"\"wordTranslation\",\"op\":\"cn\",\"data\":"+
								"\"Собака\"},{\"field\":\"usageExample\",\"op\":"+
								"\"eq\",\"data\":\"My dog is very big.\"}]}";
		try {
			filter = new ObjectMapper().readValue(searchFilters, JQGridFilter.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			List<Word> list = wordBo.findWordbygivenCriteria(filter);
			assertTrue(list.size() > 0);
			assertEquals("Dog", list.get(0).getWordName());
			assertEquals("Собака", list.get(0)
					.getWordTranslation());
			assertEquals("My dog is very big.",
					list.get(0).getUsageExample());		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void afterTest() {
		for (Word word: wordBo.findByWordName("Dog")){
			wordBo.delete(word);
		}
	}
}
