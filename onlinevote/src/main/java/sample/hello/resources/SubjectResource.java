package sample.hello.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.hello.bean.Items;
import sample.hello.bean.ResultDataList;
import sample.hello.bean.Subject;
import sample.hello.bean.business.SubjectAndItem;
import sample.hello.service.SubjectService;
import sample.hello.util.JsonUtils;

@Service
@Path("/subject")
public class SubjectResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@Autowired
	private SubjectService subjectService;
	
	//访问方法如：http://localhost:8080/onlinevote/rest/subject/1111 查询id为1111的subject
	@GET
	@Path("/{subjectId}")//资源:
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDataList<SubjectAndItem> getSubjects(@PathParam("subjectId") String subjectId){
		System.out.println("aaaaaaaaaaa"+subjectId);
		ResultDataList<SubjectAndItem> resultData = new ResultDataList<SubjectAndItem>();
		
		resultData.setStatus(0);
		resultData.setMessage("OK");
		return resultData;
	}
	
	
	@POST
	@Path("addSubect")//资源:
	@Produces(MediaType.APPLICATION_JSON)
	public String addOneSubject(String subject){
      JsonUtils jsut=new JsonUtils();
  	  Map map =new HashMap<String, Class>();
	  map.put("itemlist", Items.class);
	  Subject sres=(Subject)jsut.getObject(subject,Subject.class , map);
	  String result=subjectService.createSubject(sres);
		return result;
	}
	
	
}
