package com.itsol.mockup.services.impl;

import com.itsol.mockup.entity.RoleEntity;
import com.itsol.mockup.entity.UsersEntity;
import com.itsol.mockup.repository.UsersRepository;
import com.itsol.mockup.web.dto.auth.SpringSecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author anhvd_itsol
 */

@Service(value="userDetailsService")
public class SpringUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity user = usersRepository.getUsersByUserName(username);

        List<RoleEntity> lst = user.getRoles();
        StringBuilder test = new StringBuilder();
        lst.forEach(i -> {
            test.append("ROLE_"+i.getName());
        });

        if(user == null) {
            throw new UsernameNotFoundException(String.format("No valid user found with username '%s'.", username));
        }else {
            return new SpringSecurityUser(
                    user.getUserName(),
                    user.getPassWord(),
//                    AuthorityUtils.commaSeparatedStringToAuthorityList("MANAGER")
                    AuthorityUtils.commaSeparatedStringToAuthorityList(String.valueOf(test))
            );
        }
    }
}
