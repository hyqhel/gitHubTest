package sample.hello.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.hello.bean.Items;
import sample.hello.service.ItemService;

@Service
@Path("/subject")
public class ItemResource {
	@Autowired
	public ItemService itemService;
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	
	@GET
	@Path("Item")
	@Produces(MediaType.APPLICATION_JSON)
	public Items selectItem() {
		return itemService.queryItem("","");
	}
}
