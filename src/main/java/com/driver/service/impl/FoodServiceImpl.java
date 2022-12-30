package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class FoodServiceImpl implements FoodService
{

    @Autowired
    FoodRepository fr;
    @Override
    public FoodDto createFood(FoodDto food) {
        FoodEntity f = FoodEntity.builder().foodId(food.getFoodId()).foodName(food.getFoodName()).foodPrice(food.getFoodPrice()).foodCategory(food.getFoodCategory()).build();
        fr.save(f);
        //return null;
        return food;
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {
      FoodEntity f =  fr.findByFoodId(foodId);
      FoodDto fdto = FoodDto.builder().id(f.getId()).foodId(f.getFoodId()).foodName(f.getFoodName()).foodCategory(f.getFoodCategory()).foodPrice(f.getFoodPrice()).build();
        //return null;
        return fdto;
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {
        FoodEntity oldFood = fr.findByFoodId(foodId);

        FoodEntity newFood = FoodEntity.builder().id(oldFood.getId()).foodId(foodId).foodName(foodDetails.getFoodName()).foodPrice(foodDetails.getFoodPrice()).foodCategory(foodDetails.getFoodCategory()).build();
        fr.save(newFood);
        return foodDetails;
    }

    @Override
    public void deleteFoodItem(String id) throws Exception {
        Long x = Long.parseLong(id);
        fr.deleteById(x);
    }

    @Override
    public List<FoodDto> getFoods() {
        List<FoodEntity> food = (List<FoodEntity>)fr.findAll();
        List<FoodDto> ans = new ArrayList<>();
        int n = food.size();
        for(int i = 0; i<n; i++)
        {
            FoodDto fdto = FoodDto.builder().id(food.get(i).getId()).foodId(food.get(i).getFoodId()).foodName(food.get(i).getFoodName()).foodCategory(food.get(i).getFoodCategory()).foodPrice(food.get(i).getFoodPrice()).build();
            ans.add(fdto);
        }
        return ans;
        //return null;
    }
}