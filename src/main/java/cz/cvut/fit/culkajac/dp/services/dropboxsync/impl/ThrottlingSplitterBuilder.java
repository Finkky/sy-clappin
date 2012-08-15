package cz.cvut.fit.culkajac.dp.services.dropboxsync.impl;

import org.apache.camel.builder.RouteBuilder;
import org.switchyard.component.camel.Route;

import cz.cvut.fit.culkajac.dp.services.dropboxsync.ThrottlingSplitter;

@Route(ThrottlingSplitter.class)
public class ThrottlingSplitterBuilder extends RouteBuilder {

	private final static String SERVICE_NAME = ThrottlingSplitter.class.getSimpleName();

	@Override
	public void configure() {
		from("switchyard://" + SERVICE_NAME).split().body().throttle(1).timePeriodMillis(200).log("Logging : ${body} ${id}")
				.to("switchyard://DropboxRSServiceProxy?operationName=process");
	}
}
