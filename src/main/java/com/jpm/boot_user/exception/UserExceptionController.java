/**
 * 
 */
package com.jpm.boot_user.exception;

/**
 * @author smitkuma
 *
 */
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//AOP
@ControllerAdvice //handling exceptions globally
public class UserExceptionController {
   @ExceptionHandler(value = UserNotfoundException.class)
   public ResponseEntity<Object> exception(UserNotfoundException exception) {
      return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
   }
   @ExceptionHandler(value = MyUserException.class)
   public ResponseEntity<Object> exception(MyUserException exception) {
      return new ResponseEntity<>("User Bad request", HttpStatus.BAD_REQUEST);
   }
}