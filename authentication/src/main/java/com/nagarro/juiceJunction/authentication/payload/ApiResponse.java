/**
 * 
 */
package com.nagarro.juiceJunction.authentication.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author palak
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
	private String message;
	private boolean status;
}
