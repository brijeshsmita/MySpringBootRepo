/**
 * 
 */
package com.jpm.boot_user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.jpm.boot_user.entity.User;
import com.jpm.boot_user.exception.MyUserException;

/**
 * @author Smita B Kumar
 *JPA Repository provides CRUD operation , pagination & sorting
 */
public interface IUserDao extends JpaRepository<User, Long>{/*Entity and Primary key data type*/
	public List<User> findByEmail(String email);
	public List<User> findByFirstName(String firstName);
//public interface IUserDao extends CrudRepository<User, Long>{/*entity class , primary key(id) datatype*/
	
}
