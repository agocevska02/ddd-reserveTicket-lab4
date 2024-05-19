package com.example.ordermanagment.service;

import com.example.ordermanagment.domain.model.Order;
import com.example.ordermanagment.domain.model.OrderId;
import com.example.ordermanagment.domain.valueobjects.Ticket;
import com.example.ordermanagment.domain.valueobjects.TicketId;
import com.example.ordermanagment.domain.valueobjects.TicketType;
import com.example.ordermanagment.serivce.OrderService;
import com.example.ordermanagment.serivce.forms.OrderForm;
import com.example.ordermanagment.serivce.forms.TicketInOrderForm;
import com.example.ordermanagment.xport.client.TicketClient;
import com.example.sharedkernel.domain.base.financial.Currency;
import com.example.sharedkernel.domain.base.financial.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
 import com.example.sharedkernel.domain.base.financial.Currency;
 import  com.example.ordermanagment.domain.exceptions.OrderIdNotExistException;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class OrderServiceImplTests {

    @Autowired
    private OrderService orderService;

    @Autowired
    private TicketClient ticketClient;



    private static Ticket newTicket(TicketType type, Money price,int sales) {
        Ticket p = new Ticket(TicketId.randomId(TicketId.class), price, type,sales);
        return p;
    }

    @Test
    public void testPlaceOrder() {

        TicketInOrderForm oi1 = new TicketInOrderForm();
        oi1.setTicket(newTicket(TicketType.DRIVE_ONLY,Money.valueOf(Currency.MKD,1500),2));
        oi1.setQuantity(1);

        TicketInOrderForm oi2 = new TicketInOrderForm();
        oi2.setTicket(newTicket(TicketType.DRIVE_ONLY,Money.valueOf(Currency.MKD,500),5));
        oi2.setQuantity(2);

        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.MKD);
        orderForm.setItems(Arrays.asList(oi1,oi2));

        OrderId newOrderId = orderService.placeOrder(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(OrderIdNotExistException::new);
        Assertions.assertEquals(newOrder.total(),Money.valueOf(Currency.MKD,2500));

    }

    @Test
    public void testPlaceOrderWithRealData() {
        List<Ticket> productList = ticketClient.findAll();
        Ticket p1 = productList.get(0);
        Ticket p2 = productList.get(1);

        TicketInOrderForm oi1 = new TicketInOrderForm();
        oi1.setTicket(p1);
        oi1.setQuantity(1);

        TicketInOrderForm oi2 = new TicketInOrderForm();
        oi2.setTicket(p2);
        oi2.setQuantity(2);

        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.MKD);
        orderForm.setItems(Arrays.asList(oi1,oi2));

        OrderId newOrderId = orderService.placeOrder(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(OrderIdNotExistException::new);

        Money outMoney = p1.getPrice().multiply(oi1.getQuantity()).add(p2.getPrice().multiply(oi2.getQuantity()));
        Assertions.assertEquals(newOrder.total(),outMoney);
    }


}

