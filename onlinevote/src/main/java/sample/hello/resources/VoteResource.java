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
import sample.hello.bean.Subject;
import sample.hello.bean.business.SubjectAndItem;
import sample.hello.service.SubjectService;
import sample.hello.service.UserService;
import sample.hello.service.VoteService;
import sample.hello.util.JsonUtils;
/**
 * 投票资源
 * @author huangyq3
 *
 */
@Service
@Path("/uservote")
public class VoteResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@Autowired
	private VoteService voteService;//投票serivce
	@Autowired
	private UserService userserivce;//用户service
	
	@Autowired
	private SubjectService subjectser;
	
	//http://localhost:8080/onlinevote/rest/uservote/vote
	@POST
	@Path("vote")//资源:
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDataList<SubjectAndItem> voteOneSubjectItem(String subject){
		ResultDataList<SubjectAndItem> resultData = new ResultDataList<SubjectAndItem>();
		
      JsonUtils jsut=new JsonUtils();
  	  Map map =new HashMap<String, Class>();
	  map.put("voteItems", Items.class);
	  ItemVoteRecord votes=(ItemVoteRecord)jsut.getObject(subject,ItemVoteRecord.class , map);
	  
	  String message=jiaoYan(votes);
	  boolean rightorw=true;
	  if("".equals(message)){
		  rightorw=true;
	  }else{
		  rightorw=false;
	  }
	  if(rightorw){
		  try {
			message= voteService.userVote(votes);
			if("1".equals(message)){
				resultData.setStatus(200);
				resultData.setMessage("success");
			}else{
				resultData.setMessage("error");
			}
		} catch (Exception e) {
			resultData.setMessage("the subject not exists you vote items");
		}
	  }else{
		  resultData.setMessage(message);
	  }
		return resultData;
	}
	/**
	 * 校验
	 * @return
	 */
	public String jiaoYan(ItemVoteRecord votes){
		String message="";
		 if("null".equals(votes.getPersonId()) || "null".equals(votes.getPersonPassword()) || "null".equals(votes.getSubjectId())){
			  message="miss param!";
			  return message;
		  }
		  if("".equals(votes.getPersonId()) || "".equals(votes.getPersonPassword()) || "".equals(votes.getSubjectId())){
			  message="please write param value!";
			  return message;
		  }
		  if(votes.getVoteItems()==null || votes.getVoteItems().size()==0){
			  message="please vote the subject!";
			  return message;
		  }
		  //校验用户
		  String loginresult=userserivce.login(votes.getPersonId(), votes.getPersonPassword());
		  if("0".equals(loginresult)){
			  message="user message error!";
			  return message;
		  }
		  Subject sus=subjectser.getSubjectAndItems(votes.getSubjectId());
		  if(sus==null || sus.getSubjectId().equals("")|| null==sus.getSubjectId()){
			  message="you vote subject are not exists!";
			  return message;
		  }
		  //校验投票项与设置投票个数
		  if(Integer.parseInt(sus.getMaxSelect())<votes.getVoteItems().size()){
			  message="vote item 大于 the project maxSelect!";
			  return message;
		  }
		  return message;
	}
	
}
