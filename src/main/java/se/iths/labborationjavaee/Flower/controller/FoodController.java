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
    public List<FlowerDto> getAll() {
        return mapper.map(repository.findAll());

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOne(FlowerDto flowerDto) {

        repository.insertFlower(mapper.map(flowerDto));
        return Response.created(URI.create("flowers/" + flowerDto.getId())).build();
    }

}
