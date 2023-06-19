/**
 * 
 */
package com.nagarro.juiceJunction.authentication.exception;

/**
 * @author palak
 *
 */
@SuppressWarnings("serial")
public class WrongCredentialException extends RuntimeException{
	public WrongCredentialException(String msg) {
		super(msg);
	}
}
