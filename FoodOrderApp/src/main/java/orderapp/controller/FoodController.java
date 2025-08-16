package orderapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;

import orderapp.dto.ResponseStructure;
import orderapp.entity.Food;
import orderapp.service.FoodService;

@RestController
@RequestMapping("/food/api")
public class FoodController {
	
	@Autowired
	private FoodService foodService;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Food>> createFood(@RequestBody Food food){
		Food saved = foodService.createFood(food);
		ResponseStructure<Food> apiResponse = new ResponseStructure<>();
		apiResponse.setData(saved);
		apiResponse.setMessage("Food added successfully!");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity<ResponseStructure<Food>> getFoodById(Integer id){
		Food saved = foodService.getFootById(id);
		ResponseStructure<Food> apiResponse = new ResponseStructure<>();
		apiResponse.setData(saved);
		apiResponse.setMessage("Food fetched Successfully!");
		apiResponse.setStatusCode(HttpStatus.CREATED.value());
		
		return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<Page<Food>>> getAllFood(
			@RequestParam(defaultValue = "0",required = false) int pageNum,
			@RequestParam(defaultValue = "5", required = false) int pageSize){
		Page<Food> response = foodService.getAllFoods(pageNum,pageSize);
		ResponseStructure<Page<Food>> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setStatusCode(HttpStatus.OK.value());
		apiResponse.setMessage("Api ran successfully");
		return ResponseEntity.ok(apiResponse);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<Food>> updateFood(@RequestBody Food food,Integer id){
		Food updated = foodService.updateFood(food,id);
		ResponseStructure<Food> apiResponse = new ResponseStructure<>();
		apiResponse.setData(updated);
		apiResponse.setMessage("Food updated Successfully!");
		apiResponse.setStatusCode(HttpStatus.CREATED.value());
		
		return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/id")
	public ResponseEntity<?> deleteFood(Integer id){
		foodService.deleteFood(id);
//		ResponseStructure<Integer> apiResponse = new ResponseStructure<>();
//		apiResponse.setData();
//		apiResponse.setMessage("Food deleted Successfully!");
//		apiResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
	}
 	
}
