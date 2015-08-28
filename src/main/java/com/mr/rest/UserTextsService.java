package com.mr.rest;

import java.io.IOException;
import java.util.ArrayList;
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

import com.google.gson.Gson;
import com.mr.text.bo.TextBo;
import com.mr.text.model.Text;
import com.mr.util.Page;

//TODO
@Component
@Path("/userTexts")
public class UserTextsService {
	@Autowired
	private TextBo textBo;
	
	@Context
	private HttpServletRequest request;
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllWord(@QueryParam(value = "page.page") int page,
			@QueryParam(value = "page.size") int max){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		int defaultmax = 20;
		if (page == 0){page = 1;}
		if (max == 0){max = defaultmax;}
		String json = null; 
		List<Text> listOfTexts = new ArrayList<Text>();
		listOfTexts = textBo.loadAllUserTexts(userName);
		json = new Gson().toJson(listOfTexts);
		if (listOfTexts.size() < max){
			max = listOfTexts.size();
		}
		
		int startListFrom = 0;
		int endListWith = max;
		if (page > 1){
			startListFrom = (page - 1) * max;
			endListWith = startListFrom + max;
			if (listOfTexts.size() < endListWith){
				endListWith = listOfTexts.size();
			}
		}
		Page<Text> pageList = new Page<>(page, max, listOfTexts.size(), listOfTexts.subList(startListFrom, endListWith));  
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
	public Response addText(Text text) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		text.setUserName(userName);
		textBo.save(text);	
		return Response.status(200).build();
	}
	
	@PUT
	@Path("/{textId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateText(@PathParam("textId") int textId, Text text) {
			Text result = textBo.loadTextById(textId);
			result.setTitle(text.getTitle());
			result.setText(text.getText());
			textBo.update(result);
		return Response.status(200).build();
	}
	
	@DELETE
    @Path("/{textId}")
        public Response deleteText(@PathParam("textId") int textId) {
		  Text text= textBo.loadTextById(textId);
		  System.out.println(text);
          if(text != null){
        	  textBo.delete(text); 
          }  	
          return Response.status(200).build(); 
    }
	
	//for future consideration
	/*@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addText(String json) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		JSONObject jsonObject;
		System.out.println(json);
		Text text = new Text();
		try {
			jsonObject = new JSONObject(json);
			text.setTitle(jsonObject.getString("title"));
			text.setText(jsonObject.getString("text"));
		} catch (JSONException e) {
			e.printStackTrace();
			return Response.status(404).build();
		}
		text.setUserName(userName);
		textBo.save(text);
		System.out.println(text.getTitle()+": "+text.getText());
		return Response.status(200).build();
	}*/
	
}
