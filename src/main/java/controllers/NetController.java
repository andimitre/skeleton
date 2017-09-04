package controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/netid")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class NetController {

    @GET
    public String getNetId() {
        return "am2886";
    }
}
