package com.ivanconsalter.ionicspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ivanconsalter.ionicspring.domain.Cliente;
import com.ivanconsalter.ionicspring.repositories.ClienteRepository;
import com.ivanconsalter.ionicspring.security.UserSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ClienteRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cli = repo.findByEmail(email);
		if (cli == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSecurity(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
	}
}
