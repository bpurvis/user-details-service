package org.bp.security.spring.service;

import org.bp.security.spring.model.User;
import org.bp.security.spring.repository.UserRepository;
import org.bp.security.spring.service.model.UserDetailsImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Optional;
import java.util.TreeSet;

@Component
public class SimpleUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public SimpleUserDetailsService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String userName) {
        System.out.println("******* Finding: " + userName);
        final Optional<User> user = userRepository.findByUsername(userName);
        System.out.println("******* Found: " + user);

        if (user.isPresent()) {
            final TreeSet<SimpleGrantedAuthority> authorities = new TreeSet<>(new Comparator() {
                @Override
                public int compare(Object o, Object o2) {
                    return ((SimpleGrantedAuthority) o).getAuthority().compareTo(((SimpleGrantedAuthority) o2).getAuthority());
                }
            });

            user.get().getRoles().forEach(role -> {
                // Add the Role itself
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
                // Add all the Authority(s)
                role.getAuthorities().forEach(authority ->
                        authorities.add(new SimpleGrantedAuthority(authority.getName())));
            });

            return new UserDetailsImpl(
                    user.get().getUsername(),
                    user.get().getPassword(),
                    user.get().getAccountNonExpired(),
                    user.get().getAccountNonLocked(),
                    user.get().getCredentialsNonExpired(),
                    user.get().getAccountNonExpired(),
                    authorities);
        }

        return null;

    }
}
