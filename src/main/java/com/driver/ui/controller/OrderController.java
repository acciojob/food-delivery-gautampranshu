package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.service.OrderService;
import com.driver.service.impl.OrderServiceImpl;
import com.driver.shared.dto.FoodDto;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderServiceImpl os;

	@GetMapping(path="/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) throws Exception{
		OrderDto odto = os.getOrderById(id);
		//dto to responsedto
		OrderDetailsResponse responsedto = OrderDetailsResponse.builder().orderId(odto.getOrderId()).items(odto.getItems()).cost(odto.getCost()).userId(odto.getUserId()).status(odto.isStatus()).build();
		return responsedto;
	}
	
	@PostMapping()
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) {
		//convert requestdto to dto
		OrderDto odto = OrderDto.builder().items(order.getItems()).cost(order.getCost()).userId(order.getUserId()).build();
		odto = os.createOrder(odto);
		//dto to responsedto
		OrderDetailsResponse responsedto = OrderDetailsResponse.builder().orderId(odto.getOrderId()).items(odto.getItems()).cost(odto.getCost()).userId(odto.getUserId()).status(odto.isStatus()).build();
		return responsedto;
	}
		
	@PutMapping(path="/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) throws Exception{
		OrderDto odto = OrderDto.builder().items(order.getItems()).cost(order.getCost()).userId(order.getUserId()).build();
		odto = os.updateOrderDetails(id , odto);
		//dto to responsedto
		OrderDetailsResponse responsedto = OrderDetailsResponse.builder().orderId(odto.getOrderId()).items(odto.getItems()).cost(odto.getCost()).userId(odto.getUserId()).status(odto.isStatus()).build();
		return responsedto;
	}
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteOrder(@PathVariable String id) throws Exception {
		os.deleteOrder(id);
		return null;
	}
	
	@GetMapping()
	public List<OrderDetailsResponse> getOrders() {
		List<OrderDto> odto = os.getOrders();
		List<OrderDetailsResponse> ans = new ArrayList<>();
		int n = odto.size();
		for(int i=0; i<n; i++)
		{
			//dto to responsedto
			OrderDetailsResponse responsedto = OrderDetailsResponse.builder().orderId(odto.get(i).getOrderId()).items(odto.get(i).getItems()).cost(odto.get(i).getCost()).userId(odto.get(i).getUserId()).status(odto.get(i).isStatus()).build();
			ans.add(responsedto);
		}
		return ans;
		//return null;
	}
}
