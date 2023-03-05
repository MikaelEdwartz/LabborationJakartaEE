package se.iths.labborationjavaee.Flower.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.iths.labborationjavaee.Flower.dto.FlowerDto;
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
    public List<FlowerDto> getAll(@QueryParam("name") String name, @QueryParam("color") String color) {

        if (name == null && color == null)
            return mapper.map(repository.findAll());
        else if (name == null)
            return mapper.map(repository.findByColor(color));
        else
            return mapper.map(repository.findByName(name));
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id") Long id) {
        var flower = repository.findById(id);
        if (flower.isPresent())
            return Response.ok().entity(mapper.map(flower.get())).build();

        throw new NotFoundException("id" + id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOne(@Valid FlowerDto flowerDto) {
        var flower = mapper.map(flowerDto);
        repository.insertFlower(flower);

        return Response.created(URI.create("flowers/" + flower.getId())).build();
    }

    @DELETE
    @Path("/{id}")
    public void deleteOne(@PathParam("id") Long id) {
        repository.deleteById(id);
    }

    @PUT
    @Path("/{id}")
    public void updateFlower(@PathParam("id") Long id, @QueryParam("name") String name, @QueryParam("color") String color) {
        if (name == null && color == null) {
            throw new IllegalArgumentException("At least one parameter is required.");
        }

        if (color == null)
            repository.changeFlowerName(id, name);
        else if (name == null)
            repository.changeFlowerColor(id, color);
        else
            repository.changeFlowerAttributes(id, name, color);

    }
}
