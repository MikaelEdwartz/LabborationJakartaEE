package se.iths.labborationjavaee.Flower.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.iths.labborationjavaee.Flower.dto.FlowerDto;
import se.iths.labborationjavaee.Flower.mapper.FlowerMapper;
import se.iths.labborationjavaee.Flower.repository.FlowerRepository;
import se.iths.labborationjavaee.Flower.resources.Attributes;

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
    @ApiResponse(responseCode = "200", description = "Returns list of Flowers",
            content = @Content(schema = @Schema(implementation = FlowerDto.class)))
    public List<FlowerDto> getAll(@QueryParam("name") String name, @QueryParam("color") String color) {

        if (isAttributesNull(name, color))
            return mapper.map(repository.findAll());
        else if (name == null)
            return mapper.map(repository.findByColor(color));
        else
            return mapper.map(repository.findByName(name));

    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns flower",
                    content = @Content(schema = @Schema(implementation = FlowerDto.class))),
            @ApiResponse(responseCode = "404", description = "Id not found")})
    public Response getOne(@PathParam("id") Long id) {
        var flower = repository.findById(id);
        if (flower.isPresent())
            return Response.ok().entity(mapper.map(flower.get())).build();

        throw new NotFoundException();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponse(responseCode = "201", description = "Flower inserted")
    public Response addOne(@Valid FlowerDto flowerDto) {
        var flower = mapper.map(flowerDto);
        repository.insertFlower(flower);

        return Response.created(URI.create("flowers/" + flower.getId())).build();
    }

    @DELETE
    @Path("/{id}")
    @ApiResponse(responseCode = "200", description = "Flower deleted")
    public Response deleteOne(@PathParam("id") Long id) {
        repository.deleteById(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Flower updated",
                    content = @Content(schema = @Schema(implementation = FlowerDto.class))),
            @ApiResponse(responseCode = "404", description = "Id not found"),
            @ApiResponse(responseCode = "400", description = "No property to update")}
    )
    public Response updateFlower(@PathParam("id") Long id, @QueryParam("name") String name, @QueryParam("color") String color) {
        if (repository.findById(id).isEmpty())
            throw new NotFoundException("id:" + id);

        if (isAttributesNull(name, color))
            throw new IllegalArgumentException("At least one parameter is required.");

        if (color == null)
            repository.changeAttributes(id, name, null, Attributes.NAME);
        else if (name == null)
            repository.changeAttributes(id, null, color, Attributes.COLOR);
        else
            repository.changeAttributes(id, name, color, Attributes.BOTH);

        return Response.ok().entity(mapper.map(repository.findById(id).get())).build();
    }

    private static boolean isAttributesNull(String name, String color) {
        return name == null && color == null;
    }
}
