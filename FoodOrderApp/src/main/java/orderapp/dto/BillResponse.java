package orderapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BillResponse {
	private String restaurantName;
	private String orderSummary;
	private Float totalPrice;
}
