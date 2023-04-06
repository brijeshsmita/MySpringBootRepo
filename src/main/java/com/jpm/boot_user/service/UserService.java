/**
 * 
 */
package com.jpm.boot_user.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpm.boot_user.controller.UserController;
import com.jpm.boot_user.dao.IUserDao;
import com.jpm.boot_user.entity.User;
import com.jpm.boot_user.exception.MyUserException;

/**
 * @author Smita B Kumar
 *
 */
//prep-work -> annotate Service component
@Service("userService")
public class UserService implements IUserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
//prep work 2-> inject Dao Object
	@Autowired
	private IUserDao userDao;//loose coupling - by taking the reference of an interface

	@Override
	public Optional<User> getUserById(Long userId) throws MyUserException {
		logger.info("Trying to fetch User in service layer ");
//prep work 4-> call and return dao method in specific service method
		return userDao.findById(userId);
	}

	@Override
	@Transactional // 	@Transactional has to used only with DML -data manipulation - insert update delete
	public User addUser(User user) throws MyUserException {
		logger.info("Trying to add User in service layer " + user);

		// TODO Auto-generated method stub
		return userDao.save(user);
	}

	@Override
	@Transactional // DML
	public User updateUser(User user) throws MyUserException {
		logger.info("Trying to update User in service layer ");

		// TODO Auto-generated method stub
		Long userId = user.getId();
		Optional<User> userFound = getUserById(userId);
		User updatedUser = null;
		if (userFound.isPresent())
			updatedUser = userDao.save(user);

		return updatedUser;
	}

	@Override
	@Transactional // DML
	public void deleteUser(Long userId) throws MyUserException {
		logger.info("Trying to delete User in service layer ");
		userDao.deleteById(userId);
	}

	@Override
	public List<User> getUserList() throws MyUserException {
		logger.info("Trying to fetch all User in service layer ");
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

}
