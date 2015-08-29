package com.mr.rest;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mr.user.bo.UserBo;
import com.mr.user.model.DUser;
import com.mr.util.Page;
import com.mr.util.search.SearchUserHandler;
import com.mr.word.bo.WordBo;

@Component
@Path("/adminuserservise")
public class AdminUserServise {
	@Autowired
	private UserBo userBo;
	@Autowired
	private WordBo wordBo;
	@Autowired
	private PasswordEncoder encoder;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllWord(@QueryParam(value = "page.page") int page,
								@QueryParam(value = "page.size") int max,
								@QueryParam(value = "_search") boolean search,
								@QueryParam(value = "filters") String searchFilters){
		int defaultmax = 20;
		if (page == 0){page = 1;}
		if (max == 0){max = defaultmax;}
		List <DUser> list;
		if (search == false){
			list = userBo.loadAllUsers();
		} else {
			SearchUserHandler s = new SearchUserHandler(userBo, searchFilters);	
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
		Page<DUser> pageList = new Page<DUser>(page, max, list.size(), list.subList(startListFrom, endListWith)); 
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
	
	@PUT
	@Path("/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("userId") int userId, DUser user) {
			DUser result = userBo.loadUserById(userId);
			result.setAccess(user.getAccess());
			userBo.update(result);
		return Response.status(200).build();
	}
	
	@DELETE
    @Path("/{userId}")
        public Response deleteWord(@PathParam("userId") int userId) {
		  DUser dbuser = userBo.loadUserById(userId);
          if(dbuser != null){
        	  wordBo.deleteByUsername(dbuser.getUserName());
        	  userBo.delete(dbuser);
          }  	
          return Response.status(200).build(); 
    }
}
