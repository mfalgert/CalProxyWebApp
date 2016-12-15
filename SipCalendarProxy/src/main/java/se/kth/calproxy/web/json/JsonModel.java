package se.kth.calproxy.web.json;

import com.fasterxml.jackson.annotation.JsonView;
import se.kth.calproxy.web.google.GoogleCalendar;

/**
 * Created by Robert Norgren Erneborg on 2015-10-03.
 */
public class JsonModel {

	@JsonView(View.RestrictedView.class)
	private String name;
	@JsonView(View.RestrictedView.class)
	private String email;
	@JsonView(View.RestrictedView.class)
	private String calId;
	@JsonView(View.ExtendedView.class)
	private GoogleCalendar calendar;

	public JsonModel(){
	}

	public JsonModel(String name, String email, String calId){
		this.name = name;
		this.email = email;
		this.calId = calId;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getCalId(){
		return calId;
	}

	public void setCalId(String calId){
		this.calId = calId;
	}

	public GoogleCalendar getCalendar(){
		return calendar;
	}

	public void setCalendar(GoogleCalendar calendar){
		this.calendar = calendar;
	}

	@Override
	public String toString(){
		return "JsonModel{" +
				"name='" + name + '\'' +
				", email='" + email + '\'' +
				", calId='" + calId + '\'' +
				'}';
	}
}