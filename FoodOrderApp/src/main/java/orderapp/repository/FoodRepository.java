package orderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import orderapp.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Integer>{

}
