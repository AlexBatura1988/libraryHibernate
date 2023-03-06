package com.example.libraryHibernate.services;

import com.example.libraryHibernate.models.Person;
import com.example.libraryHibernate.repositories.PeopleRepository;
import com.example.libraryHibernate.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final PeopleRepository peopleRepository;

    public UserDetailsServiceImpl(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<Person> person = peopleRepository.findByFullName(username);

        if(person.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        //return new User(person.getFullName(), person.getPassword(), grantedAuthorities);
        return new PersonDetails(person.get());
    }
}
