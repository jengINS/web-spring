package com.tedinno.ace.repo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// import com.tedinno.ace.bind.tbl_authen;
import com.tedinno.ace.bind.tbl_users;

@Repository
public interface UsersRepository extends JpaRepository<tbl_users, Long>{

@Query(value = "SELECT * FROM tbl_users  WHERE username = :username AND password =:password", nativeQuery = true)
List<tbl_users> getLogin(@Param("username") String username, @Param("password") String password);

@Query(value = "SELECT * FROM tbl_users WHERE user_status = :user_status ", nativeQuery = true)
// @Query(value = "SELECT * FROM tbl_users  ", nativeQuery = true)
List<tbl_users> findByAP(String user_status);

@Query(value = "SELECT b.user_id, b.au_id FROM tbl_users a INNER JOIN tbl_permission b ON a.id = b.user_id WHERE a.id = 4 ", nativeQuery = true)
List<Object[]> getUserPermission();

// @Modifying
// @Transactional
// @Query(value = "insert into city (id, name) VALUES (?, ?)", nativeQuery = true)
// City save(Long id, String name);
// @Query(value = "INSERT INTO tbl_users (username, full_name) VALUES (?, ?)", nativeQuery = true)
// tbl_users save(String username, String full_name);
}

