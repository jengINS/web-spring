package com.tedinno.ace.bind;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_authen")
public class tbl_authen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long au_id;
    private Long user_insert;
    private Long user_update;
    private String au_name;
    private String au_detail;
    private LocalDateTime insert_date;
    private LocalDateTime update_date;
    private String au_status;

    public String getAu_name() {
        return au_name;
    }
}
