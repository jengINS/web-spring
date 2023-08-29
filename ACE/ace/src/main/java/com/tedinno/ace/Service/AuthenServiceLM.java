package com.tedinno.ace.Service;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tedinno.ace.bind.tbl_authen;
import com.tedinno.ace.bind.tbl_users;
import com.tedinno.ace.repo.AuthenRepository;

@Service
public class AuthenServiceLM implements AuthenService {
    @Autowired
    private AuthenRepository authenrepository;

    @Autowired
    public AuthenServiceLM(AuthenRepository authenrepository) {
        this.authenrepository = authenrepository;
    }

    @Override
    public List<tbl_authen> getAllAuthen() {
        return authenrepository.findAll();
    }

    @Override
    public List<tbl_authen> getAllPermission() {
        return authenrepository.findAll();
    }

    @Override
    public List<tbl_authen> SessionAuthen(Long au_id) {
        return authenrepository.getSessionAuthen(au_id);
    }

    @Override
    public void Permission_Status(Long s_id, Long au_id, String au_status) {
        LocalDateTime localDateTime = LocalDateTime.now();
        authenrepository.getPermissionStatus(s_id, au_id, au_status, localDateTime);
    }
}
