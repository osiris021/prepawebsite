package com.prepaWeb.Services;



import com.prepaWeb.Repositories.UserRepository.User;
import com.prepaWeb.Repositories.UserRepository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;

    public User getUserByEmail(String email) throws Exception {
        List<User> users = userRepository.findByEmail(email);
        if(users.size()==1)
        {
            return users.get(0);
        }
        else
        {
            String message = "Unable to find this login : "+email;
            logger.info(message);
            throw new Exception(message);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<User> users = userRepository.findByEmail(s);
        if(users.size()==1)
        {
            return users.get(0);
        }
        else
        {
            String message = "Unable to find this login : "+s;
            logger.info(message);
            throw new UsernameNotFoundException(message);
        }
    }
}

