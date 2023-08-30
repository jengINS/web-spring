package com.tedinno.ace.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CustomRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // public ArrayList<Object[]> executeCustomQuery(String queryString) {
    public ArrayList<Object[]> executeCustomQuery() {
        String sqlQuery = "SELECT * FROM `tbl_authen`";
        List<Object[]> resultList = jdbcTemplate.query(sqlQuery, (resultSet, rowNum) -> {
            int numColumns = resultSet.getMetaData().getColumnCount();
            Object[] row = new Object[numColumns];

            for (int i = 0; i < numColumns; i++) {
                row[i] = resultSet.getObject(i + 1); // Columns are 1-indexed in ResultSet
            }

            return row;
        });

        return new ArrayList<>(resultList);
    }

    public ArrayList<Object[]> executeCustomQuery(String queryString) {
        String sqlQuery = queryString;
        List<Object[]> resultList = jdbcTemplate.query(sqlQuery, (resultSet, rowNum) -> {
            int numColumns = resultSet.getMetaData().getColumnCount();
            Object[] row = new Object[numColumns];

            for (int i = 0; i < numColumns; i++) {
                row[i] = resultSet.getObject(i + 1); // Columns are 1-indexed in ResultSet
            }

            return row;
        });

        return new ArrayList<>(resultList);
    }

    public void updateDataCustom(Object[] updateData) {
        try {
            String sqlUpdate = "UPDATE `project_tb` SET `pm` = '" + updateData[0] + "' WHERE `project_tb`.`UID` = '"
                    + updateData[2] + "'";
            jdbcTemplate.update(sqlUpdate);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public void updateDataCustomStatusUser(Object[] updateData) {
        try {
            String sqlUpdate = "UPDATE `tbl_authen` SET `au_status` = '" + updateData[0] + "' WHERE `au_id` = "
                    + updateData[1] + "";
            jdbcTemplate.update(sqlUpdate);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public String custom_update_Boq(Object[][] updateData) {
        try {

            // Insert BOQ
            if (updateData[0][2] == "") {
                updateData[0][2] = "0";
            }

            if (updateData[0][0].equals("custom_insert_Boq")) {
                try {
                    for (var i = 1; i < updateData.length; i++) {
                        // [0][0]:update type
                        // [0][1]:dateInput
                        // [0][2]:useBoq
                        // [0][3]:boqID
                        // [0][4]:grandTotal
                        //// [1][0]:no.
                        //// [1][1]:category
                        //// [1][2]: description
                        //// [1][3]:cost
                        String sqlUpdate = "INSERT INTO `tbl_boqdetail`(`UID`,`No`, `BOQnumber`, `category`, `destcription`,`cost`) "
                                + "VALUES (UUID(),'" + updateData[i][0] + "','" + updateData[0][3] + "','"
                                + updateData[i][1]
                                + "','" + updateData[i][2] + "','" + updateData[i][3] + "')";
                        jdbcTemplate.update(sqlUpdate);
                    }

                    LocalDateTime currentDateTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
                    String currentDateTimeString = currentDateTime.format(formatter);

                    String sqlBoqList = "INSERT INTO `tbl_boq` (`id`, `user_insert`, `user_update`, `cost`, `boq_status`, `insert_date`, `update_date`, `active_status`) VALUES"
                            + " ('" + updateData[0][3] + "', " + updateData[0][2] + ", " + updateData[0][2] + ", "
                            + updateData[0][4] + ", 1, '" + currentDateTimeString + "', '" + currentDateTimeString
                            + "', 'A')";
                    jdbcTemplate.update(sqlBoqList);
                } catch (Exception ex) {
                    return "BOQ DATA ERROR";
                }
            } else if (updateData[0][0].equals("custom_Delete_Boq")) {
                String sqlUpdate = " UPDATE `tbl_boq` SET `active_status` = 'N' WHERE `tbl_boq`.`id` = '"
                        + updateData[0][1] + "'";
                jdbcTemplate.update(sqlUpdate);

            } else if (updateData[0][0].equals("custom_Edit_Boq")) {
                String sqlUpdatedel = "DELETE FROM `tbl_boqdetail` WHERE `BOQnumber`='" + updateData[0][3] + "'";
                jdbcTemplate.update(sqlUpdatedel);

                for (var i = 1; i < updateData.length; i++) {
                    // [0][0]:update type
                    // [0][1]:dateInput
                    // [0][2]:useBoq
                    // [0][3]:boqID
                    // [0][4]:grandTotal
                    //// [1][0]:no.
                    //// [1][1]:category
                    //// [1][2]: description
                    //// [1][3]:cost
                    String sqlUpdate = "INSERT INTO `tbl_boqdetail`(`UID`,`No`, `BOQnumber`, `category`, `destcription`,`cost`) "
                            + "VALUES (UUID(),'" + updateData[i][0] + "','" + updateData[0][3] + "','"
                            + updateData[i][1]
                            + "','" + updateData[i][2] + "','" + updateData[i][3] + "')";
                    jdbcTemplate.update(sqlUpdate);
                }

                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
                String currentDateTimeString = currentDateTime.format(formatter);

                String sqlBoqList = "UPDATE `tbl_boq` SET `user_update`='" + updateData[0][2] + "',`cost`="
                        + updateData[0][4] + ",`boq_status`=1,`update_date`='" + currentDateTimeString
                        + "' WHERE `id`='"
                        + updateData[0][3] + "'";
                jdbcTemplate.update(sqlBoqList);

            }
            return "success";
        }

        catch (Exception ex) {
            System.out.println(ex);
            return "DATA ERROR";
            //
        }

    }
}
