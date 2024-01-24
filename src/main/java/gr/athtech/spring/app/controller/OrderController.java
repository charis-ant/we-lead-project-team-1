package gr.athtech.spring.app.controller;

import gr.athtech.spring.app.mapper.BaseMapper;
import gr.athtech.spring.app.mapper.OrderMapper;
import gr.athtech.spring.app.model.Order;
import gr.athtech.spring.app.service.BaseService;
import gr.athtech.spring.app.service.OrderService;
import gr.athtech.spring.app.transfer.resource.OrderResource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController extends BaseController<Order, OrderResource> {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Override
    protected BaseService<Order, Long> getBaseService() {
        return orderService;
    }

    @Override
    protected BaseMapper<Order, OrderResource> getMapper() {
        return orderMapper;
    }
}
