package fr.kp.apiresthibernate.endpoint;

import fr.kp.apiresthibernate.repositories.FamilleRepository;
import fr.kp.apiresthibernate.entities.FamilleEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/familles")
@Tag(name = "familles")
public class FamilleResource {

    FamilleRepository familleRepository = new FamilleRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        List<FamilleEntity> familles = familleRepository.getAll();
        return Response.ok(familles).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id){
        FamilleEntity famille = familleRepository.getById(id);
        return Response.ok(famille).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(FamilleEntity famille){
        if(famille == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
        if (familleRepository.update(famille))
            return Response.status(204).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(FamilleEntity famille){
        if(famille == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
        if (familleRepository.create(famille) != 0)
            return Response.status(famille.getIdFamille()).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id){
        if(id == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
        if (familleRepository.delete(familleRepository.getById(id)))
            return Response.status(204).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

}
