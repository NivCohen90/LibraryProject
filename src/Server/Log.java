package Server;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Log implements Serializable{

	 String Time;
	 String Msg;
	
	public Log(String NewTime, String NewMsg) {
		this.Time = NewTime;
		this.Msg = NewMsg;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		this.Time = time;
	}
	public String getMsg() {
		return Msg;
	}
	public void setMsg(String msg) {
		this.Msg = msg;
	}
	
}
