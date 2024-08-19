package todolist.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import todolist.model.LoginUserDetails;
import todolist.service.UserService;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserService service;
	
	@Override
	public UserDetails loadUserByUsername(String username)
		throws UsernameNotFoundException {
		
		todolist.model.User loginUser = service.getLoginUser(username);
		
		if (loginUser == null) {
			throw new UsernameNotFoundException("user not found");
		}
		
		GrantedAuthority authority = new SimpleGrantedAuthority(String.valueOf(loginUser.getIsAdmin()));
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(authority);
		
//		UserDetails userDetails = (UserDetails) new User(loginUser.getUser(),
//				loginUser.getPass(),
//				authorities);
		
		UserDetails userDetails = new LoginUserDetails(loginUser);
		
		return userDetails;
	}
	
}