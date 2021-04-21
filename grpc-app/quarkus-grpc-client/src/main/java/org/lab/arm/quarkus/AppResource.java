package org.lab.arm.quarkus;

import org.lab.arm.quarkus.dto.CreatePersonDTO;
import org.lab.arm.quarkus.grpc.model.CreatePersonResponse;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/app")
public class AppResource {

    @Inject
    AppProxyGrpcService appProxyGrpcService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String health() {
        return "UP";
    }

    @POST
    @Path("/create-person")
    public String create(CreatePersonDTO request) {
        CreatePersonResponse response = appProxyGrpcService.createPerson(request);
        return response.toString();
    }
}