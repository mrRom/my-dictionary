package com.mr.util.search;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.mr.user.bo.UserBo;
import com.mr.user.model.DUser;

public class SearchUserHandler {
	private UserBo userBo;
	private String searchFilters;
	private List <DUser> list;
	
	public SearchUserHandler(UserBo userBo, String searchFilters) {
		this.searchFilters = searchFilters;
		this.userBo = userBo;
	}
	
	public List<DUser> search(){
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
				list = userBo.findUserbygivenCriteria(filter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}	
}
