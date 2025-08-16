package orderapp.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import orderapp.controller.RestaurantController;
import orderapp.entity.Food;
import orderapp.entity.Restaurant;
import orderapp.repository.FoodRepository;
import orderapp.repository.RestaurantRepository;
import orderapp.service.FoodService;

@Service
public class FoodServiceImplementation implements FoodService{

    private  RestaurantServiceImp restaurantServiceImp;
	
    private final RestaurantRepository restaurantRepository;

    private  RestaurantController restaurantController;
	
	@Autowired
	private FoodRepository foodRepository;

    FoodServiceImplementation(RestaurantController restaurantController, RestaurantRepository restaurantRepository, RestaurantServiceImp restaurantServiceImp) {
        this.restaurantController = restaurantController;
        this.restaurantRepository = restaurantRepository;
        this.restaurantServiceImp = restaurantServiceImp;
    } 
	
	@Override
	public Food createFood(Food food) {	
		return foodRepository.save(food);
	}

	@Cacheable(value="food_cache",key="#id")
	@Override
	public Food getFootById(Integer id) {
	 	Optional<Food> option = foodRepository.findById(id);
		return option.get();
	}
	
	
	@Cacheable(value="food_cache")
	@Override
	public Page<Food> getAllFoods(int pageNum, int pageSize) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<Food> page = foodRepository.findAll(pageable);
		return page;
	}


	@CachePut(value="food_cache",key ="#id")
	@Override
	public Food updateFood(Food food, Integer id) {
		Optional<Food> option = foodRepository.findById(id);
		Food fetched = option.get();
		fetched.setName(food.getName());
		fetched.setDescription(food.getDescription());
		fetched.setPrice(food.getPrice());
		
		return foodRepository.save(fetched);
	}

	@CacheEvict(value="food_cache",key="#id")
	@Override
	public void deleteFood(Integer id) {
		Food food = getFootById(id);
		List<Restaurant> restaurants = food.getRestaurants();
		if(restaurants.size()==0) {
			foodRepository.delete(food);
			return;
		}
		for (Restaurant restaurant : restaurants) {
			restaurant.getFood().remove(food);
		}
		restaurantRepository.saveAll(restaurants);
		foodRepository.delete(food);
	}
	
	@CacheEvict(value="food_cache",allEntries = true)
	@Scheduled(fixedRate = 120000)
	public void evictAllCache() {
		System.out.println("Evicting all entries form 'food_cache' cache");
	}
	


}
