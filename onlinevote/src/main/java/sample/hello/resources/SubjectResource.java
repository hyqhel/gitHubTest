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
import sample.hello.bean.ResultData;
import sample.hello.bean.ResultDataList;
import sample.hello.bean.Subject;
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
	public ResultDataList<Subject> getSubjects(@PathParam("subjectId") String subjectId){
		System.out.println("aaaaaaaaaaa"+subjectId);
		ResultDataList<Subject> resultData = new ResultDataList<Subject>();
		
		Subject subjectAndItems = subjectService.getSubjectAndItems(subjectId);
		List<Subject> rtnList = new ArrayList<Subject>();
		rtnList.add(subjectAndItems);
		
		resultData.setStatus(0);
		resultData.setMessage("OK");
		resultData.setData(rtnList);
		
		return resultData;
	}
	
	
	@POST
	@Path("addSubect")//资源:
	@Produces(MediaType.APPLICATION_JSON)
	public ResultData<Subject> addOneSubject(String subject){
		
		ResultData<Subject> resultData = new ResultData<Subject>();
		
		
      JsonUtils jsut=new JsonUtils();
  	  Map map =new HashMap<String, Class>();
	  map.put("itemlist", Items.class);
	  Subject sres=(Subject)jsut.getObject(subject,Subject.class , map);
	  
	  //校验
	  String message="";
	  boolean rightorw=true;
	  if("null".equals(sres.getSubjectContent()) || "null".equals(sres.getMaxSelect())
			  ||"null".equals(sres.getSubjectStatus())||"null".equals(sres.getType()) 
			  ||"null".equals(sres.getStartTime())||"null".equals(sres.getEndTime())){
		  message="miss param!";
		  rightorw=false;
	  }
	  if("".equals(sres.getSubjectContent())|| "".equals(sres.getMaxSelect())
			  ||"".equals(sres.getSubjectStatus())||"".equals(sres.getType()) 
			  ||"".equals(sres.getStartTime())||"".equals(sres.getEndTime())){
		  message="please write param value!";
		  rightorw=false;
	  }
	  if(sres.getItemlist()==null || sres.getItemlist().size()==0){
		  message="please add items for the subject!";
		  rightorw=false;
	  }
		if("2".equals(sres.getType())){
			 try {
				if( Integer.parseInt(sres.getMaxSelect())<1){
					message="MaxSelect must 大于 0!";
					rightorw=false;
				}
			 }catch (NumberFormatException e) {
				 message=" MaxSelect error!";
				 rightorw=false;
			  }
		  }
		if("1".equals(sres.getType())){
			sres.setMaxSelect("1");
		}
	  if(rightorw){
		  Subject suresult=subjectService.createSubject(sres);
		  if(suresult!=null){
				resultData.setStatus(200);
				resultData.setMessage("success");
				resultData.setData(suresult);
			}else{
				resultData.setMessage("error");
			}
	  }else{
		  resultData.setMessage(message);
	  }
		return resultData;
	}
	
	//访问方法如：http://localhost:8080/onlinevote/rest/subject/getAllActiveSubject
	@GET
	@Path("/getAllActiveSubject")//资源:
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDataList<Subject> getAllActiveSubject(){
		ResultDataList<Subject> resultData = new ResultDataList<Subject>();
		
		List<Subject> liss = subjectService.getAllSubject();
		
		resultData.setStatus(200);
		resultData.setMessage("success");
		resultData.setData(liss);
		
		return resultData;
	}
	
	@GET
	@Path("/selectResult/{subjectId}")//资源:
	@Produces(MediaType.APPLICATION_JSON)
	public ResultData<Subject> selectResult(@PathParam("subjectId") String subjectId){
		
		ResultData<Subject> resultData = new ResultData<Subject>();
		
		Subject bss = subjectService.getSubjectVoteResult(subjectId);
		if(null==bss){
			resultData.setStatus(200);
			resultData.setMessage("not exists ths subject");
		}else{
			resultData.setStatus(200);
			resultData.setMessage("success");
			resultData.setData(bss);
		}
		
		
		return resultData;
	}
}
