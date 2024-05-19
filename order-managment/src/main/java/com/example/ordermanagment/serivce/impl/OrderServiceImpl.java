package com.example.ordermanagment.serivce.impl;

import com.example.ordermanagment.domain.exceptions.OrderIdNotExistException;
import com.example.ordermanagment.domain.exceptions.TicketInOrderIdNotExistException;
import com.example.ordermanagment.domain.model.Order;
import com.example.ordermanagment.domain.model.OrderId;
import com.example.ordermanagment.domain.model.TicketInOrderId;
import com.example.ordermanagment.domain.repository.OrderRepository;
import com.example.ordermanagment.domain.valueobjects.TicketId;
import com.example.ordermanagment.serivce.OrderService;
import com.example.ordermanagment.serivce.forms.OrderForm;
import com.example.ordermanagment.serivce.forms.TicketInOrderForm;
import com.example.sharedkernel.domain.base.events.orders.TicketInOrderCreated;
import com.example.sharedkernel.domain.infra.DomainEventPublisher;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Transactional

public class OrderServiceImpl implements OrderService {

    private  final OrderRepository orderRepository;
    private final Validator validator;
    private final DomainEventPublisher domainEventPublisher;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, Validator validator,DomainEventPublisher publisher) {
        this.orderRepository = orderRepository;
        this.validator = validator;
        this.domainEventPublisher=publisher;
    }




    @Override
    public OrderId placeOrder(OrderForm orderForm) {
        Objects.requireNonNull(orderForm,"order must not be null.");
        var constraintViolations = validator.validate(orderForm);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException("The order form is not valid", constraintViolations);
        }
        var newOrder = orderRepository.saveAndFlush(toDomainObject(orderForm));
        newOrder.getTicketInOrderList().forEach(item->domainEventPublisher.
                publish(new TicketInOrderCreated(item.getTicketId().getId(),item.getQuantity(),item.getDate_of_reservation())));
        return newOrder.getId();
    }



    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(OrderId orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public void addTicket(OrderId orderId, TicketInOrderForm ticketInOrderForm) throws OrderIdNotExistException {

        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.addTicket(ticketInOrderForm.getTicket(),ticketInOrderForm.getQuantity(),ticketInOrderForm.getDate_of_reservation());
        orderRepository.saveAndFlush(order);
        domainEventPublisher.publish(new TicketInOrderCreated(ticketInOrderForm.getTicket().getId().getId(),ticketInOrderForm.getQuantity(),ticketInOrderForm.getDate_of_reservation()));

    }

    @Override
    public void deleteTicket(OrderId orderId, TicketInOrderId ticketInOrderId) throws TicketInOrderIdNotExistException, OrderIdNotExistException {

        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.removeTicket(ticketInOrderId);
        orderRepository.saveAndFlush(order);

    }
    private Order toDomainObject(OrderForm orderForm) {
        var order = new Order(Instant.now(),orderForm.getCurrency());
        orderForm.getItems().forEach(item->order.addTicket(item.getTicket(),item.getQuantity(),item.getDate_of_reservation()));
        return order;
    }

}
