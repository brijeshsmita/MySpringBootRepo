/**
 * 
 */
package com.jpm.boot_user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jpm.boot_user.entity.User;
import com.jpm.boot_user.exception.MyUserException;
import com.jpm.boot_user.exception.UserNotfoundException;
import com.jpm.boot_user.service.IUserService;

/**
 * @author Smita B Kumar
 *@RequestMapping (@Path)
 *	-used at class level
 *	-method level
 *-spring 4.3 onward method level request mapping made more simpler
 *@GetMapping -Get method
 *@PostMapping -Post method
 *@PutMapping -Put method
 *@DeleteMapping -Delete method
 *http://localhost:8082/
 */
/*This @CrossOrigin annotation enables cross-origin requests only for controller or the specific method. By default, its allows all origins, all headers, the HTTP methods specified in the @RequestMapping annotation and a maxAge of 30 minutes is used. You can customize this behavior by specifying the value of one of the annotation attributes: origins, methods, allowedHeaders, exposedHeaders, allowCredentials or maxAge. In this example, we only allow http://localhost:8082 to send cross-origin requests.*/
//prep-work 1> @CrossOrigin
/**
 * author: smitkuma Description : Rest controller for WalletUser created Date:
 * 18/10/2019 Controller with different HTTP methods as GET,POST,DELETE and
 * their respective URL mappings class level request mapping as "walletAccounts"
 * return type is JSON
 */
@CrossOrigin(origins = {"http://localhost:4200"}, 
allowedHeaders = "*") // prep-work 2> @RestController
@RestController
//prep-work 3> @RequestMapping
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private IUserService userService;

	// http://localhost:8082/api-users
	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/") public String greetUser() { return
	 * "Hello From Spring Boot Rest User Application"; }
	 */
	// http://localhost:8082/api-users/
	@CrossOrigin("*")
	@GetMapping(path = "/", produces = "application/json")
	public ResponseEntity<List<User>> getAllUsers() throws UserNotfoundException {
		logger.info("Trying to fetch User list ");
		List<User> users =null;
		try {
			users = userService.getUserList();

			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Record NOT found : ");
			return new ResponseEntity<List<User>>(users, HttpStatus.NOT_FOUND);
		}
		/*
		 * List<User> userList = new ArrayList<>(); userList.add(new User(22L, "admin",
		 * "admin", "admin@gmail.com")); return userList;
		 */
	}

// http://localhost:8082/api-users/1	
	
	@GetMapping(path = "/{id}", produces = "application/json")
	// value passed along with url ,variable passed along with path
	public ResponseEntity<User> getUserById(@PathVariable Long id) throws UserNotfoundException {
		Optional<User> user = null;
		logger.info("Trying to search Record with Id : " + id);
		try {
			user = userService.getUserById(id);

			if (user.isPresent()) {
				return new ResponseEntity<>(user.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error("Record NOT Found with Id : " + id);
			return new ResponseEntity<User>(new User(), HttpStatus.EXPECTATION_FAILED);
		}
	}

	// http://localhost:8082/api-users/add
	// default input - json
	@PostMapping(path = "/add")
	public ResponseEntity<User> addUser(@RequestBody User user) throws UserNotfoundException {
		try {
			logger.info("Trying to add Record  : " + user);
			User addedUser = userService.addUser(user);
			return new ResponseEntity<>(user, HttpStatus.CREATED);//201
		} catch (Exception e) {
			logger.error("Record NOT Added  : " + user);
			return new ResponseEntity<>(user, HttpStatus.EXPECTATION_FAILED);
			
		}
	}

	// http://localhost:8082/api-users/1
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) throws UserNotfoundException {
		
		try {
			userService.deleteUser(id);
			Optional<User> delUser = userService.getUserById(id);
			logger.info("Record Deleted with Id : " + id);
			return new ResponseEntity<>("Record Deleted...with id : "+id,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Record NOT Deleted with Id : " + id);
			return new ResponseEntity<>("Record not found with id : "+id,HttpStatus.EXPECTATION_FAILED);
		}
	}

	// http://localhost:8082/api-users/1
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable Long id)
			throws UserNotfoundException {
		logger.info("trying to update user : " + user);
		try {
			Optional<User> userFound = userService.getUserById(id);

			if (userFound.isPresent()) {
				userService.updateUser(user);
				System.out.println("Record Updated : " + user);
				return ResponseEntity.ok(user);
			} else {
				return new ResponseEntity<>("Record NOT updated with Id : " + user,HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			logger.error("Record NOT updated with Id : " + user);
			return new ResponseEntity<>("Record NOT updated with Id : " + user, HttpStatus.EXPECTATION_FAILED);
		}

	}

}

/*
 * Class ResponseEntity<T>. public class ResponseEntity<T> extends HttpEntity<T>
 * Extension of HttpEntity that adds a HttpStatus status code. Used in
 * RestTemplate as well @Controller methods. Defines a builder that adds a body
 * to the response entity. Defines a builder that adds headers to the response
 * entity.
 */
