package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.service.FoodService;
import com.driver.service.impl.FoodServiceImpl;
import com.driver.shared.dto.FoodDto;
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
@RequestMapping("/foods")
public class FoodController {

	@Autowired
	FoodServiceImpl fs;

	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{
		FoodDto fdto = fs.getFoodById(id);
		//convert to food response dto
		FoodDetailsResponse responsedto = FoodDetailsResponse.builder().foodId(fdto.getFoodId()).foodName(fdto.getFoodName()).foodPrice(fdto.getFoodPrice()).foodCategory(fdto.getFoodCategory()).build();
		//return null;
		return responsedto;
	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {
		//convert requestdto to dto
		FoodDto fdto = FoodDto.builder().foodName(foodDetails.getFoodName()).foodPrice(foodDetails.getFoodPrice()).foodCategory(foodDetails.getFoodCategory()).build();
		fdto = fs.createFood(fdto);
		FoodDetailsResponse responsedto = FoodDetailsResponse.builder().foodId(fdto.getFoodId()).foodName(fdto.getFoodName()).foodPrice(fdto.getFoodPrice()).foodCategory(fdto.getFoodCategory()).build();
		return responsedto;
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{
		FoodDto fdto = FoodDto.builder().foodId(id).foodPrice(foodDetails.getFoodPrice()).foodCategory(foodDetails.getFoodCategory()).foodName(foodDetails.getFoodName()).build();
		fdto = fs.updateFoodDetails(id , fdto);
		FoodDetailsResponse responsedto = FoodDetailsResponse.builder().foodId(fdto.getFoodId()).foodName(fdto.getFoodName()).foodPrice(fdto.getFoodPrice()).foodCategory(fdto.getFoodCategory()).build();
		//return null;
		return responsedto;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception{
		OperationStatusModel obj = new OperationStatusModel();
		fs.deleteFoodItem(id);
		//return null;
		return obj;
	}
	
	@GetMapping()
	public List<FoodDetailsResponse> getFoods() {
		List<FoodDto> dto = fs.getFoods();
		List<FoodDetailsResponse> ans = new ArrayList<>();
		int n = dto.size();
		for(int i=0; i<n; i++)
		{
			FoodDetailsResponse responsedto = FoodDetailsResponse.builder().foodId(dto.get(i).getFoodId()).foodName(dto.get(i).getFoodName()).foodPrice(dto.get(i).getFoodPrice()).foodCategory(dto.get(i).getFoodCategory()).build();
			ans.add(responsedto);
		}
		return ans;
	}
}
