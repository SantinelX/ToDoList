package com.todolist.todolist.config;

import com.todolist.todolist.model.Role;
import com.todolist.todolist.model.User;
import com.todolist.todolist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserAutenticationService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserAutenticationService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        User user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException(String.format("User with name %s not found !!!", username));
        }
        return mapUserToUserDetails(user);
    }
    private UserDetails mapUserToUserDetails(User user) {
        List<GrantedAuthority> userAutorities = maprRoleToGrantedAutority(user.getRole());

        UserDetails userDetails = new org.springframework.security.core.userdetails.User
                (user.getUsername(), user.getPassword(), userAutorities);
        return userDetails;
    }

    private List<GrantedAuthority> maprRoleToGrantedAutority(Collection<Role> roleList) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role : roleList) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getRole());
            grantedAuthorities.add(simpleGrantedAuthority);
        }
        return grantedAuthorities;
    }
}
