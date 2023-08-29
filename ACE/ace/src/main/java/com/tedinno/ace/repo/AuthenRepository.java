package com.tedinno.ace.repo;


import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tedinno.ace.bind.tbl_authen;

@Repository
public interface AuthenRepository extends JpaRepository<tbl_authen, Long> {
    @Query(value = "SELECT * FROM tbl_authen WHERE au_id = :au_id ", nativeQuery = true)
    List<tbl_authen> getSessionAuthen(Long au_id);

    @Transactional
    @Modifying
    @Query(value ="UPDATE tbl_authen  SET au_status = :au_status, user_insert = :user_insert, update_date = :update_date WHERE au_id = :au_id", nativeQuery=true)
    int getPermissionStatus(@Param("user_insert") Long user_insert, @Param("au_id") Long au_id, @Param("au_status") String au_status ,@Param("update_date") LocalDateTime update_date);
}
