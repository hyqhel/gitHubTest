package sample.hello.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import sample.hello.bean.ResultDataList;
import sample.hello.bean.business.SubjectAndItem;
import sample.hello.service.SubjectService;

@Path("/subject")
public class SubjectResource {

	@Autowired
	private SubjectService subjectService;
	
	@GET
	@Path("getSubjects")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDataList<SubjectAndItem> getSubjects(){
		ResultDataList<SubjectAndItem> resultData = new ResultDataList<SubjectAndItem>();
		
		resultData.setStatus(0);
		resultData.setMessage("OK");
		
		List<SubjectAndItem> list =new ArrayList<SubjectAndItem>();
		SubjectAndItem ite=new SubjectAndItem(); 
		ite.setItemContent("124");
		ite.setItemId("222");
		List<String> listit=new ArrayList<String>();
		listit.add("111");
		listit.add("22");
		ite.setListItme(listit);
		list.add(ite);
		
		SubjectAndItem itee=new SubjectAndItem(); 
		itee.setItemContent("124");
		itee.setItemId("222");
		List<String> listits=new ArrayList<String>();
		listits.add("111");
		listits.add("22");
		itee.setListItme(listits);
		list.add(itee);
		
		resultData.setData(list);
		
		return resultData;
	}
}
