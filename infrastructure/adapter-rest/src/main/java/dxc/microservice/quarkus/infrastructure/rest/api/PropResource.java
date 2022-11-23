package dxc.microservice.quarkus.infrastructure.rest.api;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import dxc.microservice.quarkus.application.service.IProperService;
import dxc.microservice.quarkus.domain.model.Prop;
import dxc.microservice.quarkus.infrastructure.rest.dto.PropDto;
import dxc.microservice.quarkus.infrastructure.rest.mapper.PropMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Path("/api/v1/props")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Props", description = "Halloween props operations")
@AllArgsConstructor
@Slf4j
public class PropResource {

        IProperService propService;

        PropMapper propMapper;

        @GET
        @Path("/{id}")
        @APIResponse(responseCode = "200", description = "Get prop by id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.OBJECT, implementation = PropDto.class)))
        @APIResponse(responseCode = "204", description = "Prop does not exist for id", content = @Content(mediaType = MediaType.APPLICATION_JSON))
        public PropDto getProp(@PathParam("id") Long id) {                
                return propMapper.toDto(propService.getProp(id));
        }

        @GET
        @Path("/")
        @APIResponse(responseCode = "200", description = "Get All Props", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.ARRAY, implementation = PropDto.class)))
        public List<PropDto> getAllProps() {
                return propService.getAllProps().stream().map(p -> propMapper.toDto(p))
                                .collect(Collectors.toList());
        }

        @PUT
        @Path("/{id}/activate")
        @APIResponse(responseCode = "200", description = "Prop has been activated", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.ARRAY, implementation = PropDto.class)))
        @APIResponse(responseCode = "204", description = "Prop does not exist for id", content = @Content(mediaType = MediaType.APPLICATION_JSON))
        public PropDto activateProp(@PathParam("id") Long id) {
                return propMapper.toDto(propService.activateProp(id));
        }

        @POST
        @APIResponse(responseCode = "201", description = "Prop Created", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.OBJECT, implementation = PropDto.class)))
        @APIResponse(responseCode = "400", description = "Invalid Prop", content = @Content(mediaType = MediaType.APPLICATION_JSON))
        @APIResponse(responseCode = "400", description = "Prop already exists", content = @Content(mediaType = MediaType.APPLICATION_JSON))
        public Response post(@NotNull @Valid PropDto prop, @Context UriInfo uriInfo) {
                if (null != prop.getId() && null != propService.getProp(prop.getId())) {
                        log.warn("Prop with id {} already exists. Skipping creation.", prop.getId());
                        return Response.status(Response.Status.BAD_REQUEST).build();
                }

                Prop propDomain = propMapper.toDomain(prop);
                propService.save(propDomain);
                PropDto propDto = propMapper.toDto(propDomain);
                URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(propDto.getId())).build();

                return Response.created(uri).entity(propDto).build();
        }
}