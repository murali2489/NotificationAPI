package com.billing.portal.notification.contract;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.billing.portal.notification.entities.Notification;

@Consumes("application/json")
@Produces("application/json")
@Path("/NotifyAPIService")
public interface NotificationService {
	
	@Path("/notify")
	@POST
	public Response Notify(Notification notify);

	@Path("/listNotifications")
	@GET
	public List<Notification> NotifyAPI();
	
	@Path("/startBatch")
    @GET
    public Response invokeBatch();
	
}
