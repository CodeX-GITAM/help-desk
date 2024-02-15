package com.aarambh.helpdesk.service.implementation;

import com.aarambh.helpdesk.dao.UserDAO;
import com.aarambh.helpdesk.dao.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository ;

    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optionalUserEntity = userRepository.findByUsername(username);
        if (optionalUserEntity.isEmpty())
            throw new UsernameNotFoundException(username);
        UserDAO userDAO = optionalUserEntity.get();
        return new User(userDAO.getUsername(), userDAO.getPassword(), List.of(new SimpleGrantedAuthority(userDAO.getUsername())));
    }
}
