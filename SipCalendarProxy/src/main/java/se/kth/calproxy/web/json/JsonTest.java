package se.kth.calproxy.web.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Robert Norgren Erneborg on 2015-10-03.
 */
public class JsonTest {

	private static String home = System.getProperty("user.home");
	public static ObjectMapper mapper;
	public static void main(String[] args){
		mapper = new ObjectMapper();
		mapper.setConfig(mapper.getSerializationConfig().withView(View.RestrictedView.class));
//		mapper.setConfig(mapper.getSerializationConfig().withView(View.ExtendedView.class));
	}


	public static void read() throws IOException{
		List<JsonModel> jsonModels = mapper.readValue(new File(home + "\\" + "users.se.kth.calproxy.web.config"), new TypeReference<List<JsonModel>>(){});
		for (JsonModel j : jsonModels)
			System.out.println(j);
	}

	public static void write() throws IOException{
		LinkedList<JsonModel> list = new LinkedList<>();
		JsonModel jm;
		jm = new JsonModel("robert","robert@kth.se","def");
		jm.setCalendar(null);
		list.add(jm);
		jm = new JsonModel("marcus","marcus@kth.se","primary");
		jm.setCalendar(null);
		list.add(jm);
		jm = new JsonModel("erneborg","erneborg@kth.se","kth");
		jm.setCalendar(null);
		list.add(jm);
		jm = new JsonModel("project-user","proxysipcal@kth.se","standard");
		jm.setCalendar(null);
		list.add(jm);

		mapper.writeValue(new File(home + "\\" + "users.se.kth.calproxy.web.config"), list);

	}

}
