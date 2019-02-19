package com.game.JoseMosquera.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.game.JoseMosquera.controller.LoginController;
import com.game.JoseMosquera.converter.UserConverter;
import com.game.JoseMosquera.entity.Role;
import com.game.JoseMosquera.model.UserModel;
import com.game.JoseMosquera.repository.RoleRepository;
import com.game.JoseMosquera.repository.UserRepository;
import com.game.JoseMosquera.service.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserDetailsService, UserService{
	
	private static final Log LOG = LogFactory.getLog(LoginController.class);
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;
	
	@Autowired
	@Qualifier("roleRepository")
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.game.JoseMosquera.entity.User user = userRepository.findByUsername(username);
		List<GrantedAuthority> authorities = buildAuthorities(user.getUserRole());
		return buildUser(user, authorities);
	}
	
	private User buildUser(com.game.JoseMosquera.entity.User user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnable(), true, true, true, authorities);
	}
	
	private List<GrantedAuthority> buildAuthorities(Set<Role> userRoles){
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		
		for(Role userRole : userRoles) {
			auths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return new ArrayList<GrantedAuthority>(auths);
	}

	@Override
	public com.game.JoseMosquera.model.UserModel addUser(UserModel userModel) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		LOG.info("Metodo addUser() recive una pass sin encriptar: "+userModel.getPassword());
		String pass = pe.encode(userModel.getPassword());
		userModel.setPassword(pass);
		userModel.setEnable(true);
		LOG.info("Metodo addUser() devuelve una pass encriptada: "+userModel.getPassword());
		com.game.JoseMosquera.entity.User user = userRepository.save(userConverter.model2entity(userModel));
		roleRepository.save(new Role(user, "ROLE_USER"));
		return userConverter.entity2model(user);
	}
	
	
}
