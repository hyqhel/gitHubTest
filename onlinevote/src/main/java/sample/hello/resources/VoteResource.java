package sample.hello.resources;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.hello.bean.ItemVoteRecord;
import sample.hello.bean.Items;
import sample.hello.bean.ResultDataList;
import sample.hello.bean.business.SubjectAndItem;
import sample.hello.service.VoteService;
import sample.hello.util.JsonUtils;

@Service
@Path("/uservote")
public class VoteResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@Autowired
	private VoteService voteService;
	
	
	@POST
	@Path("vote")//资源:
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDataList<SubjectAndItem> voteOneSubjectItem(String subject){
		ResultDataList<SubjectAndItem> resultData = new ResultDataList<SubjectAndItem>();
		
      JsonUtils jsut=new JsonUtils();
  	  Map map =new HashMap<String, Class>();
	  map.put("voteItems", Items.class);
	  ItemVoteRecord votes=(ItemVoteRecord)jsut.getObject(subject,ItemVoteRecord.class , map);
	  
	  String message="";
	  boolean rightorw=true;
	  if("null".equals(votes.getPersonId()) || "null".equals(votes.getPersonPassword()) || "null".equals(votes.getSubjectId())){
		  message="miss param!";
		  rightorw=false;
	  }
	  if("".equals(votes.getPersonId()) || "".equals(votes.getPersonPassword()) || "".equals(votes.getSubjectId())){
		  message="please write param value!";
		  rightorw=false;
	  }
	  if(votes.getVoteItems()==null || votes.getVoteItems().size()==0){
		  message="please vote the subject!";
		  rightorw=false;
	  }
	  if(rightorw){
		  message= voteService.userVote(votes);;
		  if("1".equals(message)){
				resultData.setStatus(200);
				resultData.setMessage("success");
			}else{
				resultData.setMessage("error");
			}
	  }else{
		  resultData.setMessage(message);
	  }
		return resultData;
	}
	
	
}
