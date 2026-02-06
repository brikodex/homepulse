package com.homepulse.backend.resource;

import com.homepulse.backend.dto.SupportConversationDto;
import com.homepulse.backend.dto.SupportConversationRequest;
import com.homepulse.backend.dto.SupportConversationStatusRequest;
import com.homepulse.backend.dto.SupportMessageDto;
import com.homepulse.backend.dto.SupportMessageRequest;
import com.homepulse.backend.model.SupportConversation;
import com.homepulse.backend.model.SupportMessage;
import com.homepulse.backend.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/support")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SupportResource {
    @PersistenceContext
    private EntityManager entityManager;

    @POST
    @Path("/conversations")
    @Transactional
    public Response createConversation(SupportConversationRequest request) {
        if (request == null || request.getUserId() == null || request.getSubject() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        User user = entityManager.find(User.class, request.getUserId());
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }
        SupportConversation conversation = new SupportConversation();
        conversation.setUser(user);
        conversation.setSubject(request.getSubject());
        entityManager.persist(conversation);
        return Response.status(Response.Status.CREATED).entity(toConversationDto(conversation)).build();
    }

    @GET
    @Path("/conversations")
    public List<SupportConversationDto> getAllConversations() {
        List<SupportConversation> conversations = entityManager.createQuery(
                        "select c from SupportConversation c join fetch c.user",
                        SupportConversation.class)
                .getResultList();
        return conversations.stream().map(this::toConversationDto).collect(Collectors.toList());
    }

    @GET
    @Path("/conversations/user/{userId}")
    public Response getUserConversations(@PathParam("userId") Long userId) {
        List<SupportConversation> conversations = entityManager.createQuery(
                        "select c from SupportConversation c join fetch c.user where c.user.id = :userId",
                        SupportConversation.class)
                .setParameter("userId", userId)
                .getResultList();
        return Response.ok(conversations.stream().map(this::toConversationDto).collect(Collectors.toList())).build();
    }

    @PUT
    @Path("/conversations/{id}/status")
    @Transactional
    public Response updateConversationStatus(@PathParam("id") Long id, SupportConversationStatusRequest request) {
        SupportConversation conversation = entityManager.find(SupportConversation.class, id);
        if (conversation == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (request == null || request.getStatus() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        conversation.setStatus(request.getStatus());
        return Response.noContent().build();
    }

    @POST
    @Path("/messages")
    @Transactional
    public Response createMessage(SupportMessageRequest request) {
        if (request == null || request.getConversationId() == null || request.getMessage() == null || request.getSenderRole() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        SupportConversation conversation = entityManager.find(SupportConversation.class, request.getConversationId());
        if (conversation == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Conversation not found").build();
        }
        SupportMessage message = new SupportMessage();
        message.setConversation(conversation);
        message.setSenderRole(request.getSenderRole());
        message.setMessage(request.getMessage());
        entityManager.persist(message);
        return Response.status(Response.Status.CREATED).entity(toMessageDto(message)).build();
    }

    @GET
    @Path("/messages/conversation/{conversationId}")
    public Response getConversationMessages(@PathParam("conversationId") Long conversationId) {
        List<SupportMessage> messages = entityManager.createQuery(
                        "select m from SupportMessage m join fetch m.conversation c where c.id = :conversationId order by m.createdAt",
                        SupportMessage.class)
                .setParameter("conversationId", conversationId)
                .getResultList();
        return Response.ok(messages.stream().map(this::toMessageDto).collect(Collectors.toList())).build();
    }

    private SupportConversationDto toConversationDto(SupportConversation conversation) {
        return new SupportConversationDto(
                conversation.getId(),
                conversation.getUser().getId(),
                conversation.getSubject(),
                conversation.getStatus(),
                conversation.getCreatedAt());
    }

    private SupportMessageDto toMessageDto(SupportMessage message) {
        return new SupportMessageDto(
                message.getId(),
                message.getConversation().getId(),
                message.getSenderRole(),
                message.getMessage(),
                message.getCreatedAt());
    }
}
