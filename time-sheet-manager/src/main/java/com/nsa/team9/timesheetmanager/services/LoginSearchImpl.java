package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.Login;
import com.nsa.team9.timesheetmanager.repositories.LoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public void createLogin(Login login) {

    }

    @Override
    public Optional<Login> findById(Long loginId){
        return loginRepository.findById(loginId);
    }

    @Override
    public List<Login> findAllLoginEmails() {
        return null;
    }

    @Override
    public Optional<Login> getLoginByEmail(String email) {
        return loginRepository.getLoginByEmail(email);
    }


}
