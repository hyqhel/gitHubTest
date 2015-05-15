package sample.hello.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResultData<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int status;//返回 0：失败；1：成�?

	private T data;//具体的对�?
	
	private String message;//错误时消�?

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
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

	public ResultData() {
		super();
	}

	public ResultData(int status, T data, String message) {
		super();
		this.status = status;
		this.data = data;
		this.message = message;
	}

}
