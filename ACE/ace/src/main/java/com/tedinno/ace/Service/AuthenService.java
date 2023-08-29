package com.tedinno.ace.Service;

import java.util.List;

import com.tedinno.ace.bind.tbl_authen;
import com.tedinno.ace.bind.tbl_users;

public interface AuthenService {
    public List<tbl_authen> getAllAuthen();
    public List<tbl_authen> getAllPermission();
    public void Permission_Status(Long s_id, Long au_id, String au_status);
    public List<tbl_authen> SessionAuthen(Long au_id);
}
