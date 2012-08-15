package cz.cvut.fit.culkajac.dp.binding.restful.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("status")
@Named
@RequestScoped
public class FileServiceStatus {

	@GET
	@Path("/")
	public Response status() {
		return Response.ok().build();
	}

}
