package com.homepulse.backend.resource;

import com.homepulse.backend.dto.AuthLoginRequest;
import com.homepulse.backend.dto.AuthResponse;
import com.homepulse.backend.dto.UserCreateRequest;
import com.homepulse.backend.dto.UserDto;
import com.homepulse.backend.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {
    @PersistenceContext
    private EntityManager entityManager;

    @POST
    @Path("/register")
    @Transactional
    public Response register(UserCreateRequest request) {
        if (request == null || request.getEmail() == null || request.getPassword() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        List<User> existing = entityManager
                .createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", request.getEmail())
                .getResultList();
        if (!existing.isEmpty()) {
            return Response.status(Response.Status.CONFLICT).entity("Email already registered").build();
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole() == null ? "CUSTOMER" : request.getRole());
        entityManager.persist(user);

        return Response.status(Response.Status.CREATED)
                .entity(new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole()))
                .build();
    }

    @POST
    @Path("/login")
    public Response login(AuthLoginRequest request) {
        if (request == null || request.getEmail() == null || request.getPassword() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        List<User> users = entityManager
                .createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", request.getEmail())
                .getResultList();
        if (users.isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials").build();
        }

        User user = users.get(0);
        if (!request.getPassword().equals(user.getPassword())) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials").build();
        }

        String tokenSource = user.getEmail() + ":" + UUID.randomUUID();
        String token = Base64.getEncoder().encodeToString(tokenSource.getBytes(StandardCharsets.UTF_8));
        UserDto userDto = new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole());

        return Response.ok(new AuthResponse(token, userDto)).build();
    }
}
