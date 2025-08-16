package orderapp.service;


import org.springframework.data.domain.Page;

import orderapp.entity.Food;

public interface FoodService {
	
	Food createFood(Food food);

	Food getFootById(Integer id);

	Page<Food> getAllFoods(int pageNum, int pageSize);

	Food updateFood(Food food, Integer id);

	void deleteFood(Integer id);
}
