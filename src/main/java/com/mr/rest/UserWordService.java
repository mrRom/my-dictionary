package com.mr.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.mr.util.Page;
import com.mr.util.search.SearchWordHandler;
import com.mr.word.bo.WordBo;
import com.mr.word.model.Word;

@Component
@Path("/userWord")
public class UserWordService {
	@Autowired
	private WordBo wordBo;
	
	@Context
    private HttpServletRequest request;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllWord(@QueryParam(value = "page.page") int page,
								@QueryParam(value = "page.size") int max,
								@QueryParam(value = "_search") boolean search,
								@QueryParam(value = "filters") String searchFilters){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		int defaultmax = 20;
		if (page == 0){page = 1;}
		if (max == 0){max = defaultmax;}
		List <Word> list;
		if (search == false){
			list = wordBo.selectAllUserWords(userName);
		} else {
			list = new SearchWordHandler(wordBo, searchFilters, userName).search();
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
		//System.out.println(json);
		return Response.status(200).entity(json).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addWord(Word word) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		word.setUserName(userName);
		wordBo.save(word);	
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
			wordBo.update(result);
		return Response.status(200).build();
	}
	
	@DELETE
    @Path("/{wordId}")
        public Response deleteWord(@PathParam("wordId") int wordId) {
		  Word dbword = wordBo.findWordById(wordId);
          if(dbword != null){
        	  wordBo.delete(dbword); 
          }  	
          return Response.status(200).build(); 
    }
}
