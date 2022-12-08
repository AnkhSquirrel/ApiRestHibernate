package fr.kp.apiresthibernate.endpoint;

import fr.kp.apiresthibernate.entities.ClassificationEntity;
import fr.kp.apiresthibernate.repositories.ClassificationRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/classifications")
@Tag(name = "classifications")
public class ClassificationResource {

    ClassificationRepository classificationRepository = new ClassificationRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        List<ClassificationEntity> classifications = classificationRepository.getAll();
        return Response.ok(classifications).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id){
        ClassificationEntity classification = classificationRepository.getById(id);
        return Response.ok(classification).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(ClassificationEntity classification){
        if(classification == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
        if (classificationRepository.update(classification))
            return Response.status(204).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(ClassificationEntity classification){
        if(classification == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
        if (classificationRepository.create(classification) != 0)
            return Response.status(classification.getIdClassification()).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id){
        if(id == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
        if (classificationRepository.delete(classificationRepository.getById(id)))
            return Response.status(204).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

}
