package com.example.hexagonal.adapters.in.web;

import com.example.hexagonal.application.ports.in.OrderUseCase;
import com.example.hexagonal.domain.model.Order;
import com.example.hexagonal.domain.model.OrderItem;
import com.example.hexagonal.application.service.OrderService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderController {

    @Inject
    OrderUseCase orderUseCase;

    @POST
    public Response createOrder(Order order) {
        orderUseCase.createOrder(order);
        return Response.status(Response.Status.CREATED).entity(order).build();
    }

    @GET
    @Path("/{orderId}")
    public Response getOrderById(@PathParam("orderId") Long orderId) {
        Optional<Order> value = orderUseCase.findOrderById(orderId);
        if (value.isPresent()) {
            return Response.ok(value.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public Response getAllOrders() {
        List<Order> orders = orderUseCase.getAllOrders();
        return Response.ok(orders).build();
    }

    @DELETE
    @Path("/{orderId}")
    public Response deleteOrder(@PathParam("orderId") Long orderId) {
        orderUseCase.deleteOrder(orderId);
        return Response.ok().build();
    }


}
