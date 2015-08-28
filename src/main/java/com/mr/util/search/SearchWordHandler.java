package com.mr.util.search;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.mr.word.bo.WordBo;
import com.mr.word.model.Word;

//???threadsafe???
public class SearchWordHandler {
    private WordBo wordBo;
	private String searchFilters;
	private List <Word> list;
	private String userName = null;
	
	public SearchWordHandler(WordBo wordBo, String searchFilters) {
		this.searchFilters = searchFilters;
		this.wordBo = wordBo;
	}
	
	public SearchWordHandler(WordBo wordBo, String searchFilters, String userName) {
		this.searchFilters = searchFilters;
		this.wordBo = wordBo;
		this.userName = userName;
	}
		
		/*{"groupOp":"AND",
		 * "rules":[{"field":"wordName","op":"eq","data":"111"},
		 * {"field":"wordTranslation","op":"cn","data":"111"},
		 * {"field":"usageExample","op":"eq","data":"111"}]}
		 */
	
	public List<Word> search(){
		JQGridFilter filter = new JQGridFilter();
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
			if (userName!=null){
				list = wordBo.findWordbygivenCriteria(userName, filter);
			} else {
				list = wordBo.findWordbygivenCriteria(filter);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}	
}