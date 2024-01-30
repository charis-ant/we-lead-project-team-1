package gr.athtech.spring.app.controller;

import gr.athtech.spring.app.mapper.BaseMapper;
import gr.athtech.spring.app.mapper.OrderMapper;
import gr.athtech.spring.app.model.Order;
import gr.athtech.spring.app.model.Status;
import gr.athtech.spring.app.service.BaseService;
import gr.athtech.spring.app.service.OrderService;
import gr.athtech.spring.app.transfer.ApiResponse;
import gr.athtech.spring.app.transfer.resource.OrderResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(params = {"accountId"})
    public ResponseEntity<ApiResponse<List<OrderResource>>> findByAccountId(@RequestParam Long accountId) {
        return ResponseEntity.ok(
                ApiResponse.<List<OrderResource>>builder()
                        .data(orderMapper.toResources(orderService.findByAccountId(accountId)))
                        .build());
    }

    @GetMapping(params = {"storeId"})
    public ResponseEntity<ApiResponse<List<OrderResource>>> findByStoreId(@RequestParam Long storeId) {
        return ResponseEntity.ok(
                ApiResponse.<List<OrderResource>>builder()
                        .data(orderMapper.toResources(orderService.findByStoreId(storeId)))
                        .build());
    }

    @PatchMapping(params = "orderId, status")
    public ResponseEntity<ApiResponse<OrderResource>> changeStatus(@RequestParam Long orderId, @RequestParam Status status) {
        orderService.changeStatus(orderId, status);
        return new ResponseEntity<>(
                getNoCacheHeaders(),
                HttpStatus.ACCEPTED
        );
    }

    @PatchMapping(params = "orderId, orderRating")
    public ResponseEntity<ApiResponse<OrderResource>> rateOrder(@RequestParam Long orderId, @RequestParam Integer orderRating) {
        orderService.rateOrder(orderId, orderRating);
        return new ResponseEntity<>(
                getNoCacheHeaders(),
                HttpStatus.ACCEPTED
        );
    }

}
