package com.example.ordermanagment.serivce;

import com.example.ordermanagment.domain.exceptions.OrderIdNotExistException;
import com.example.ordermanagment.domain.exceptions.TicketInOrderIdNotExistException;
import com.example.ordermanagment.domain.model.Order;
import com.example.ordermanagment.domain.model.OrderId;
import com.example.ordermanagment.domain.model.TicketInOrderId;
import com.example.ordermanagment.serivce.forms.OrderForm;
import com.example.ordermanagment.serivce.forms.TicketInOrderForm;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderId placeOrder(OrderForm orderForm);
    List<Order> findAll();
    Optional<Order> findById(OrderId orderId);
    void addTicket(OrderId orderId, TicketInOrderForm ticketInOrderForm) throws OrderIdNotExistException;
    void deleteTicket(OrderId orderId, TicketInOrderId ticketInOrderId) throws TicketInOrderIdNotExistException,OrderIdNotExistException;

}
