package io.branch.github;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
@Path("/api/v1/ping")
public class PingResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ping() {
		PingGET pingGet = new PingGET();
		pingGet.setStatus(PingGET.DEFAULT_STATUS);

		return Response.ok()
				.entity(pingGet)
				.build();
	}

	private class PingGET {
		public static final String DEFAULT_STATUS = "Healthy";

		private String status;

		public String getStatus() {
			return status;
		}

		public void setStatus(final String status) {
			this.status = status;
		}
	}
}