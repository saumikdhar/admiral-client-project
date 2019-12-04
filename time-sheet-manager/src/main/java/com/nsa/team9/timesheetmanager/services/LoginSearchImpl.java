package com.nsa.team9.timesheetmanager.services;

import com.nsa.team9.timesheetmanager.domain.Login;
import com.nsa.team9.timesheetmanager.repositories.LoginRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginSearchImpl implements LoginSearch{

    private LoginRepository loginRepository;

    public LoginSearchImpl(LoginRepository aRepo){loginRepository=aRepo;}

    @Transactional
    @Override
    public void createLogin(Login aLogin) {
        loginRepository.save(aLogin);
    }
}
