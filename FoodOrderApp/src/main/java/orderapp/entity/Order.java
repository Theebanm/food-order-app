package orderapp.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import orderapp.enums.OrderStatus;

@Entity
@Data
@Table(name = "Orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItem;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@JsonIgnore
	@ManyToOne
	private Restaurant restaurant;
	
	private Double totalPrice;
	
	@JsonIgnore
	@ManyToOne
	private User user;
}
