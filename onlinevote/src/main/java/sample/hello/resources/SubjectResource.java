package sample.hello.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import sample.hello.bean.ResultData;
import sample.hello.bean.business.SubjectAndItem;
import sample.hello.service.SubjectService;

@Path("/subject")
public class SubjectResource {

	@Autowired
	private SubjectService subjectService;
	
	@GET
	@Path("getSubjects")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultData<SubjectAndItem> getSubjects(){
		ResultData<SubjectAndItem> resultData = new ResultData<SubjectAndItem>();
		
		resultData.setStatus(0);
		resultData.setMessage("OK");
		
		SubjectAndItem ite=new SubjectAndItem(); 
		ite.setItemContent("124");
		ite.setItemId("222");
		resultData.setData(ite);
		
		return resultData;
	}
}
