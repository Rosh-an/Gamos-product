package com.stackroute.service;

import com.stackroute.dao.UserDao;

import com.stackroute.model.DAOUser;
import com.stackroute.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**mention below class is a Service */
@Service
public class JwtUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    /**loads user by user name */
    @Override
    public UserDetails loadUserByUsername(String username)  {
        DAOUser user = userDao.findByUserEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getPassword(),
                new ArrayList<>());
    }
    /**for saving the userDAO */
    public DAOUser save(UserDTO user) {
        DAOUser newUser = new DAOUser();
        logger.info(user.getPassword());
        newUser.setUserEmail(user.getUserEmail());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setLoginType(user.getLoginType());
        return userDao.save(newUser);
    }
    /**for deleting the userDAO */
    public void delete(DAOUser user){
        DAOUser newUser = userDao.findByUserEmail(user.getUserEmail());
        logger.info("Deleted:  {}",newUser);
        userDao.deleteById((int) newUser.getId());
    }
}
