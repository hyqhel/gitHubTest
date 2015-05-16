package sample.hello.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResultDataList<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int status;//返回 0：失败；1：成�?

	private List<T> data;//具体的对�?
	
	private String message;//错误时消�?

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data =  data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResultData [status=" + status + ", data=" + data + ", message="
				+ message + "]";
	}

	public ResultDataList() {
		super();
	}

	public ResultDataList(int status, List<T> data, String message) {
		super();
		this.status = status;
		this.data = data;
		this.message = message;
	}

}
