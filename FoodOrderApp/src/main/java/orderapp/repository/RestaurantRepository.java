package orderapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import orderapp.entity.Food;
import orderapp.entity.Order;
import orderapp.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	
	@Query("SELECT r.food FROM Restaurant r WHERE r.id = :restaurantId")
	public List<Food> findFoodByRestaurantId(@Param(value = "restaurantId") Integer id);
	
	@Query("SELECT r.orders  FROM Restaurant r WHERE r.id = :restaurantId")
	public List<Order> findOrdersFindRestaurantId(@Param(value = "restaurantId") Integer id);
}
