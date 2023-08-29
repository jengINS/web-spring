package com.tedinno.ace.Service;

import java.util.List;

import com.tedinno.ace.bind.tbl_authen;
import com.tedinno.ace.bind.tbl_users;

public interface UsersService {

public List<tbl_users> login(String username, String password);
public List<tbl_users> getAllEmployee();
public List<tbl_users> getUserList();
public void save(tbl_users Tbl_users);
public tbl_users getById(Long id);
public List<Object[]> UserPermission();



}
