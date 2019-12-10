package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.Login;
import com.nsa.team9.timesheetmanager.repositories.LoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class LoginSearchImpl  implements LoginSearch{

    private LoginRepository loginRepository;

    @Autowired
    public LoginSearchImpl(LoginRepository aRepo){
        loginRepository = aRepo;
    }

    @Transactional
    public void searchLogin(Login login){
        loginRepository.equals(login);
    }


    @Override
    public Optional<Login> findById(Long loginId){
        return loginRepository.findById(loginId);
    }

    @Override
    public Optional<Login> getLoginByEmail(String email) {
        return loginRepository.getLoginByEmail(email);
    }


    @Transactional
    @Override
    public void createLogin(Login aLogin) {
        loginRepository.save(aLogin);
    }

    @Override
    public Optional<Login> findEmailExists(String email) {
        return loginRepository.findEmailExists(email);
    }

    @Override
    public void updateUserPassword(Long loginId, String newPassword) {
         loginRepository.updateUserPassword(loginId, newPassword);
    }

}
