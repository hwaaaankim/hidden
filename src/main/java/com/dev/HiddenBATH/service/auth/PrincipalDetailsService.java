package com.dev.HiddenBATH.service.auth;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dev.HiddenBATH.model.PrincipalDetails;
import com.dev.HiddenBATH.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,InternalAuthenticationServiceException  {
		
		log.info("PrincipalDetailService.loadUserByUsername");
		
		if(!memberRepository.findByUsername(username).isPresent()) {
			throw new UsernameNotFoundException(username);
		}
		
		return new PrincipalDetails(memberRepository.findByUsername(username).get());
	}
	
}
