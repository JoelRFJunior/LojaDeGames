package org.generation.minhaLojaDeGames.security;

import java.util.Optional;

import org.generation.minhaLojaDeGames.model.Usuario;
import org.generation.minhaLojaDeGames.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> user = userRepository.findByEmail(email);
		user.orElseThrow(() -> new UsernameNotFoundException(email + "not found"));
		
		return user.map(UserDetailsImpl::new).get();
	}
}

