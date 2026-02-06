package com.homepulse.backend.resource;

import com.homepulse.backend.dto.OrderCreateRequest;
import com.homepulse.backend.dto.OrderDto;
import com.homepulse.backend.dto.OrderItemDto;
import com.homepulse.backend.dto.OrderItemRequest;
import com.homepulse.backend.dto.OrderStatusUpdateRequest;
import com.homepulse.backend.model.Order;
import com.homepulse.backend.model.OrderItem;
import com.homepulse.backend.model.Product;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {
    @PersistenceContext
    private EntityManager entityManager;

    @POST
    @Transactional
    public Response createOrder(OrderCreateRequest request) {
        if (request == null || request.getUserId() == null || request.getItems() == null || request.getItems().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        User user = entityManager.find(User.class, request.getUserId());
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }

        Order order = new Order();
        order.setUser(user);
        order.setStatus(request.getStatus() == null ? "PENDING" : request.getStatus());

        BigDecimal total = BigDecimal.ZERO;
        List<OrderItem> items = new ArrayList<>();
        for (OrderItemRequest itemRequest : request.getItems()) {
            if (itemRequest.getProductId() == null || itemRequest.getQuantity() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid order item").build();
            }
            Product product = entityManager.find(Product.class, itemRequest.getProductId());
            if (product == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Product not found").build();
            }
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemRequest.getQuantity());
            item.setPrice(product.getPrice());
            items.add(item);
            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())));
        }
        order.setTotalAmount(total);
        order.setItems(items);
        entityManager.persist(order);

        return Response.status(Response.Status.CREATED).entity(toDto(order)).build();
    }

    @GET
    public List<OrderDto> getAllOrders() {
        List<Order> orders = entityManager.createQuery(
                        "select distinct o from Order o left join fetch o.user left join fetch o.items i left join fetch i.product",
                        Order.class)
                .getResultList();
        return orders.stream().map(this::toDto).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public Response getOrderById(@PathParam("id") Long id) {
        List<Order> orders = entityManager.createQuery(
                        "select distinct o from Order o left join fetch o.user left join fetch o.items i left join fetch i.product where o.id = :id",
                        Order.class)
                .setParameter("id", id)
                .getResultList();
        if (orders.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(toDto(orders.get(0))).build();
    }

    @PUT
    @Path("/{id}/status")
    @Transactional
    public Response updateOrderStatus(@PathParam("id") Long id, OrderStatusUpdateRequest request) {
        Order order = entityManager.find(Order.class, id);
        if (order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (request == null || request.getStatus() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        order.setStatus(request.getStatus());
        return Response.noContent().build();
    }

    @GET
    @Path("/user/{userId}")
    public Response getUserOrders(@PathParam("userId") Long userId) {
        List<Order> orders = entityManager.createQuery(
                        "select distinct o from Order o left join fetch o.user left join fetch o.items i left join fetch i.product where o.user.id = :userId",
                        Order.class)
                .setParameter("userId", userId)
                .getResultList();
        return Response.ok(orders.stream().map(this::toDto).collect(Collectors.toList())).build();
    }

    private OrderDto toDto(Order order) {
        List<OrderItemDto> itemDtos = order.getItems().stream()
                .map(item -> new OrderItemDto(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPrice()))
                .collect(Collectors.toList());
        return new OrderDto(order.getId(), order.getUser().getId(), order.getStatus(), order.getTotalAmount(), order.getCreatedAt(), itemDtos);
    }
}
