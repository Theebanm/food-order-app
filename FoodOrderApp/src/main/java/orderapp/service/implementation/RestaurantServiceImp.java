package orderapp.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import orderapp.entity.Food;
import orderapp.entity.Order;
import orderapp.entity.Restaurant;
import orderapp.repository.FoodRepository;
import orderapp.repository.RestaurantRepository;
import orderapp.service.RestaurantService;

@Service
public class RestaurantServiceImp implements RestaurantService{

 @Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private FoodRepository foodRepository;


	@Override
	public Restaurant createRestaurant(Restaurant restaurant) {

		Restaurant restaurent = restaurantRepository.save(restaurant);
		return  restaurent;
	}   

	@Cacheable(value = "restaurent_cache" ,key = "#id")
	@Override
	public Restaurant getById(Integer id) {
		Optional<Restaurant> response = restaurantRepository.findById(id);
		
		if(response.isPresent()) {
			return response.get();
		}
		else {
			throw new NoSuchElementException("Restaurant with ID: "+id+" not found");
		}
		
//		return restaurantRepository.findById(id).orElseThrow(()->new NoSuchElementException("Restaurant with ID: "+id+" not found"));
	}

	
	@Cacheable(value="restaurent_cache")
	@Override
	public Page getAllRestaurants(int pageNum, int pageSize, String sortBy) {
		Sort sort = Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
		Page page = restaurantRepository.findAll(pageable);
		
		return page;
	}

	@CachePut(value="restaurent_cache",key ="#id")
	@Override
	public Restaurant updateRestaurant(Integer id, Restaurant updatedRestaurant) {
		Restaurant existing = getById(id);
		existing.setAddress(updatedRestaurant.getAddress());
		existing.setContactNumber(updatedRestaurant.getContactNumber());
		existing.setEmail(updatedRestaurant.getEmail());
		existing.setName(updatedRestaurant.getName());
		
		return restaurantRepository.save(existing);
	}
	
	@CacheEvict(value = "restaurent_cache",key="#id")
	@Override
	public void deleteRestaurant(Integer id) {
		Restaurant restaurant = getById(id);
		restaurantRepository.delete(restaurant);
	}

	@CacheEvict(value = "restaurent_cache",key="#foodId")
	@Override
	public Restaurant assignFood(Integer restaurantId, Set<Integer> foodId) {
		Restaurant restaurant = getById(restaurantId);
		
		List<Food> foodItems = new ArrayList();
		
		for(Integer id : foodId) {
			Food food = foodRepository.findById(id).orElseThrow(()->new NoSuchElementException("Food with ID: "+id+" not found"));
			foodItems.add(food);
		}
		
		restaurant.setFood(foodItems);
		
		return restaurantRepository.save(restaurant);
	}

	@Cacheable(value ="restaurent_cache",key="#restaurantId")
	@Override
	public List<Food> findFoodByRestaurantId(Integer restaurantId) {
		List<Food> foods = restaurantRepository.findFoodByRestaurantId(restaurantId);
		if(foods == null || foods.size()==0) {
			throw new NoSuchElementException("Restaurant with Id "+restaurantId+" not found or the food is not assigned to restaurant");
		}else {
			return foods;
		}
	}

	@Cacheable(value="restaurent_cache", key="#restaurantId")
	@Override
	public List<Order> findOrderByRestaurantId(Integer restaurantId) {
		List<Order> orders = restaurantRepository.findOrdersFindRestaurantId(restaurantId);
		if(orders == null  || orders.isEmpty()) {
			throw new NoSuchElementException("Restaurant with id "+restaurantId+" not found");
		}
		return orders;
	}

	@CacheEvict(value="restaurent_cache",allEntries = true)
	@Scheduled(fixedRate = 120000)
	
	public void evictAllCache() {
		System.out.println("Evicting all entries form 'restaurent_cache' cache");
	}
	
	
}
