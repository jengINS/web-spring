package com.tedinno.ace.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tedinno.ace.bind.tbl_authen;
import com.tedinno.ace.bind.tbl_users;
import com.tedinno.ace.repo.UsersRepository;

@Service
public class UsersServiceLM implements UsersService{
    @Autowired
    private UsersRepository usersrepository ;

    @Override public List<tbl_users> login(String username, String password) {
        return usersrepository.getLogin(username, password);
    }

    @Override public List<tbl_users> getUserList() {
        return usersrepository.findByAP("A");
    }    


    @Override public List<Object[]>  UserPermission() {
        List<Object[]> results = usersrepository.getUserPermission();
        return results;
    }

    @Override public void save(tbl_users tbl_users) {
        usersrepository.save(tbl_users);
    }    

    @Override public List<tbl_users> getAllEmployee() {
        return usersrepository.findAll();
    }

    @Override public tbl_users getById(Long id) {
        Optional<tbl_users> optional = usersrepository.findById(id);
        tbl_users Tbl_users = null;
        
        if (optional.isPresent())
            Tbl_users = optional.get();
        else
            throw new RuntimeException(
                    "Employee not found for id : " + id);
        return Tbl_users;
    }
}
