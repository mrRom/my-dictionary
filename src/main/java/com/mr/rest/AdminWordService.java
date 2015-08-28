package com.mr.rest;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mr.user.bo.UserBo;
import com.mr.user.model.DUser;
import com.mr.util.Page;
import com.mr.util.search.SearchWordHandler;
import com.mr.word.bo.WordBo;
import com.mr.word.model.Word;

@Component
@Path("/adminwordservise")
public class AdminWordService {
	@Autowired
	WordBo wordBo;
	@Autowired
	UserBo userBo;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllWord(@QueryParam(value = "page.page") int page,
								@QueryParam(value = "page.size") int max,
								@QueryParam(value = "_search") boolean search,
								@QueryParam(value = "filters") String searchFilters){
		int defaultmax = 20;
		if (page == 0){page = 1;}
		if (max == 0){max = defaultmax;}
		List <Word> list;
		if (search == false){
			list = wordBo.selectAllWords();
		} else {
			SearchWordHandler s = new SearchWordHandler(wordBo, searchFilters);	
			list = s.search();
		}
		
		if (list.size() < max){
			max = list.size();
		}
		
		int startListFrom = 0;
		int endListWith = max;
		if (page > 1){
			startListFrom = (page - 1) * max;
			endListWith = startListFrom + max;
			if (list.size() < endListWith){
				endListWith = list.size();
			}
		}
		
		Page<Word> pageList = new Page<>(page, max, list.size(), list.subList(startListFrom, endListWith)); 
		String json = null; 
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			json = ow.writeValueAsString(pageList);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(json).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addWord(Word word) {
		DUser u = userBo.loadUserByUsername(word.getUserName());
		if (u!=null){
			wordBo.save(word);	
		}
		return Response.status(200).build();
	}
	
	@PUT
	@Path("/{wordId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateWord(@PathParam("wordId") int wordId, Word word) {
			Word result = wordBo.findWordById(wordId);
			result.setWordName(word.getWordName());
			result.setWordTranslation(word.getWordTranslation());
			result.setUsageExample(word.getUsageExample());
			if (!result.getUserName().equalsIgnoreCase(word.getUserName())){
				DUser u = userBo.loadUserByUsername(word.getUserName());
				if (u!=null){
					result.setUserName(word.getUserName());
				}
			}
			wordBo.update(result);
		return Response.status(200).build();
	}
	
	@DELETE
    @Path("/{wordId}")
    public Response deleteWord(@PathParam("wordId") int wordId) 
    {
		  Word dbword = wordBo.findWordById(wordId);
          if(dbword != null){
        	  wordBo.delete(dbword); 
          }  	
          return Response.status(200).build(); 
    }
	@GET
	@Path("/selectusernames")
	@Produces(MediaType.APPLICATION_XHTML_XML)
	public Response selectUserNames(){
		StringBuilder sb = new StringBuilder();
		sb.append("<select>");
		List<DUser> list = userBo.loadAllUsers();
		for (DUser u: list){
			sb.append("<option value='"+u.getUserName()+"'>"+u.getUserName()+"</option>");
		}
		sb.append("</select>");
		return Response.status(200).entity(sb.toString()).build();
	}
}
