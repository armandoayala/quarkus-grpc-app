package org.lab.arm.quarkus.api.resource;

import org.lab.arm.quarkus.api.dto.CreatePersonRequest;
import org.lab.arm.quarkus.api.dto.CreatePersonResponse;
import org.lab.arm.quarkus.api.service.AppService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/quarkus-api")
public class AppResource {

    @Inject
    AppService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String health() {
        return "UP";
    }

    @POST
    @Path("/create-person")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CreatePersonRequest request) {

        try {
            CreatePersonResponse createPersonResponse = service.create(request);
            return Response.ok(createPersonResponse).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new CreatePersonResponse(null, false, ex.getMessage())).build();
        }

    }
}