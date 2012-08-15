package cz.cvut.fit.culkajac.dp.binding.restful;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

public interface FileServiceRESTBinding {

	@GET
	Response getFile(String title);

	@POST
	Response postFile(HttpServletRequest request);

	@GET
	Response deleteFile(boolean delete, String title);

	@DELETE
	Response deleteFile(String title);

}
