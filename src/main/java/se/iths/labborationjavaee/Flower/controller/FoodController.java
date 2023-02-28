package se.iths.labborationjavaee.Flower.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.iths.labborationjavaee.Flower.dto.FlowerDto;
import se.iths.labborationjavaee.Flower.entity.Flower;
import se.iths.labborationjavaee.Flower.mapper.FlowerMapper;
import se.iths.labborationjavaee.Flower.repository.FlowerRepository;

import java.net.URI;
import java.util.List;

@Path("/flowers")
public class FoodController {

    @Inject
    FlowerRepository repository;

    @Inject
    FlowerMapper mapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FlowerDto> getAll(@QueryParam("name") String name) {
        if (name == null)
            return mapper.map(repository.findAll());

        return mapper.map(repository.findByName(name));

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOne(Flower flower) {

        repository.insertFlower(flower);
        return Response.created(URI.create("flowers/" + flower.getId())).build();
    }

    @DELETE
    @Path("/{id}")
    public void deleteOne(@PathParam("id") Long id) {
        repository.deleteById(id);
    }

}
