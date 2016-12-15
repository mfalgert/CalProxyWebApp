package se.kth.calproxy.web.Controller;

import com.google.api.client.util.DateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.kth.calproxy.web.google.GoogleCalendar;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Robert Norgren Erneborg on 2015-10-10.
 */

@RestController
@RequestMapping("/")
public class MainController {

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public String displayRequestPage()
	{
		return "Server is up";
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerUser(@RequestParam("gmail") String email, @RequestParam("sip-user") String sipUser) {

		GoogleCalendar gcal = null;
		try{
			gcal = new GoogleCalendar(email, sipUser);
			gcal.getCalendarEvents(new DateTime(System.currentTimeMillis()));
		}catch (IOException e){
		}

		return "OK";
	}

}
