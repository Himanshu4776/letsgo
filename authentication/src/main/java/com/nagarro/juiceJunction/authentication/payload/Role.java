/**
 * 
 */
package com.nagarro.juiceJunction.authentication.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
	private Integer id;
	private String name;
}
