package com.example.dnlab.service.auth;

import com.example.dnlab.domain.User;
import com.example.dnlab.domain.auth.PrincipalDetails;
import com.example.dnlab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public PrincipalDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        User user = userRepository.findByUid(uid);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with uid: " + uid);
        }
        return new PrincipalDetails(user);
    }
}
