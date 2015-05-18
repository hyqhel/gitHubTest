package sample.hello.testresource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;
/**
 * 测试根据一个subjectId 取subject 详细信息
 * @author huangyq3
 *
 */
public class TestGetSubjet {
	/**
	 * 测试查询一个主题投票结果
	 */
	@Test
  public void testGetSubjectVoteResult(){
		URL url = null;
		         try {
		            url = new URL("http://localhost:8080/onlinevote/rest/subject/selectResult/hPFaT488c5VPySNG");
		             HttpURLConnection connet;
		             connet = (HttpURLConnection) url.openConnection();
		             if(connet.getResponseCode() != 200){
		                 throw new IOException(connet.getResponseMessage());
		             }
		             //将返回的值存入到String中
		            BufferedReader brd = new BufferedReader(new InputStreamReader(connet.getInputStream()));
		 
		             System.out.println(brd.readLine());
		 
		             connet.disconnect();
		         } catch (IOException e) {
		            // TODO Auto-generated catch block
		             e.printStackTrace();
		         }
  }
	/**
	 * 测试查询所有可以投票的主题
	 */
	@Test
	  public void testGetActiveVoteSubject(){
			URL url = null;
			         try {
			            url = new URL("http://localhost:8080/onlinevote/rest/subject/getAllActiveSubject");
			             HttpURLConnection connet;
			             connet = (HttpURLConnection) url.openConnection();
			             if(connet.getResponseCode() != 200){
			                 throw new IOException(connet.getResponseMessage());
			             }
			             //将返回的值存入到String中
			            BufferedReader brd = new BufferedReader(new InputStreamReader(connet.getInputStream()));
			 
			             System.out.println(brd.readLine());
			 
			             connet.disconnect();
			         } catch (IOException e) {
			            // TODO Auto-generated catch block
			             e.printStackTrace();
			         }
	  }
	
	/**
	 * 测试查询某一主题详细信息
	 */
	@Test
	  public void testGetOneSubjectMessage(){
			URL url = null;
			         try {
			            url = new URL("http://localhost:8080/onlinevote/rest/subject/hPFaT488c5VPySNG");
			             HttpURLConnection connet;
			             connet = (HttpURLConnection) url.openConnection();
			             if(connet.getResponseCode() != 200){
			                 throw new IOException(connet.getResponseMessage());
			             }
			             //将返回的值存入到String中
			            BufferedReader brd = new BufferedReader(new InputStreamReader(connet.getInputStream()));
			 
			             System.out.println(brd.readLine());
			 
			             connet.disconnect();
			         } catch (IOException e) {
			            // TODO Auto-generated catch block
			             e.printStackTrace();
			         }
	  }
	
	/**
	 * 测试投票
	 */
	@Test
	  public void testVoteSubject() throws Exception{
		String query="";
		StringBuffer bs=new StringBuffer();
		bs.append("{");
		bs.append("personId:\"1\",");
		bs.append("personPassword:\"123456\",");
		bs.append(" subjectId:\"sBRZHorl0TMEFMIy\",");
		bs.append("voteItems:[");
		bs.append("{");
		bs.append("itemId:\"YKhxt4QYjouLeFEa\"");
		bs.append(" }, ");
		bs.append(" {");
		bs.append(" itemId:\"WHami6OEyWt3gwJs\"");
		bs.append("}");
		bs.append("]");
		bs.append("}");
		query=bs.toString();
		URL restURL = new URL("http://localhost:8080/onlinevote/rest/uservote/vote"); 
        HttpURLConnection conn = (HttpURLConnection) restURL.openConnection(); 
        conn.setRequestMethod("POST"); 
        conn.setDoOutput(true); 
        conn.setAllowUserInteraction(false); 
        PrintStream ps = new PrintStream(conn.getOutputStream()); 
        ps.print(query); 
        ps.close(); 
        BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
        String line,resultStr=""; 
        while(null != (line=bReader.readLine())) 
        { 
            resultStr +=line; 
        } 
        bReader.close(); 
		System.out.println(resultStr);
	  }
	/**
	 * 测试创建主题
	 */
	@Test
	  public void testAddSubject() throws Exception{
		String query="";
		StringBuffer bs=new StringBuffer();
		bs.append("{");
		bs.append(" subjectContent:\"去骑车吗\",");
		bs.append(" subjectStatus:\"0\",");
		bs.append("startTime:\"2015-4-3 20:29:00\",");
		bs.append("endTime:\"2015-6-3 20:29:00\",");
		bs.append(" type:\"1\",");
		bs.append("  maxSelect:\"2\",");
		bs.append("itemlist:[");
		bs.append("{");
		bs.append(" content:\"骑\"");
		bs.append(" }, ");
		bs.append(" {");
		bs.append(" content:\"不骑\"");
		bs.append("},");
		bs.append(" {");
		bs.append("  content:\"可骑可不骑\"");
		bs.append("}");
		
		bs.append("]");
		bs.append("}");
		query=bs.toString();
		URL restURL = new URL("http://localhost:8080/onlinevote/rest/subject/addSubect"); 
        HttpURLConnection conn = (HttpURLConnection) restURL.openConnection(); 
        conn.setRequestMethod("POST"); 
        conn.setDoOutput(true); 
        conn.setAllowUserInteraction(false); 
        PrintStream ps = new PrintStream(conn.getOutputStream()); 
        ps.print(query); 
        ps.close(); 
        BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
        String line,resultStr=""; 
        while(null != (line=bReader.readLine())) 
        { 
            resultStr +=line; 
        } 
        bReader.close(); 
		System.out.println(resultStr);
	  }
}
