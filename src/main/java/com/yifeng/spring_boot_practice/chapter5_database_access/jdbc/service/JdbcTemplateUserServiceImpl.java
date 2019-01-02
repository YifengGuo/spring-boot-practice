package com.yifeng.spring_boot_practice.chapter5_database_access.jdbc.service;

import com.yifeng.spring_boot_practice.chapter5_database_access.jdbc.enumeration.SexEnum;
import com.yifeng.spring_boot_practice.chapter5_database_access.jdbc.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * Created by guoyifeng on 1/2/19.
 */
@Service
public class JdbcTemplateUserServiceImpl implements JdbcTemplateUserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * map a database row into POJO
     * @return
     */
    private RowMapper<User> getUserMapper() {
        RowMapper<User> userRowMapper = (ResultSet rs, int rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setNote(rs.getString("note"));
            user.setSex(SexEnum.getEnumById(rs.getInt("sex")));
            user.setUsername(rs.getString("user_name"));
            return user;
        };
        return userRowMapper;
    }

    public User getUser(Long id) {
        // sql
        String sql = "SELECT id, user_name, sex, note FROM t_user WHERE id = ?";

        // params for sql
        Object[] params = new Object[]{id};

        User user = jdbcTemplate.queryForObject(sql, params, getUserMapper());

        return user;

    }

    /**
     * execute multiple sql during one database connection
     * @param id
     * @return
     */
    public User getUserByStatementCallback(Long id) {
        User result = this.jdbcTemplate.execute(((Statement stmt) -> {
            String sql1 = "SELECT COUNT(*) AS total FROM t_user WHERE id = " + id;
            ResultSet rs1 = stmt.executeQuery(sql1);
            while (rs1.next()) {
                int total = rs1.getInt("total");
                System.out.println(total);
            }

            String sql2 = "SELECT * FROM t_user WHERE id = " + id;
            ResultSet rs2 = stmt.executeQuery(sql2);
            User user = null;
            while (rs2.next()) {
                int rowNum = rs2.getRow();
                user = getUserMapper().mapRow(rs2, rowNum);
            }
            return user;
        }));
        return result;
    }

    /**
     * difference between Statement and PreparedStatement:
     * https://stackoverflow.com/questions/3271249/difference-between-statement-and-preparedstatement
     * @param id
     * @return
     */
    public User getUserByConnectionCallback(Long id) {
        User result = this.jdbcTemplate.execute((Connection conn) -> {
            String sql1 = "SELECT COUNT(*) AS total FROM t_user WHERE id = ?";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setLong(1, id);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                int total = rs1.getInt("total");
                System.out.println(total);
            }

            String sql2 = "SELECT * FROM t_user WHERE id = ?";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setLong(1, id);
            ResultSet rs2 = ps2.executeQuery();
            User user = null;
            while (rs2.next()) {
                int rowNum = rs2.getRow();
                user = getUserMapper().mapRow(rs2, rowNum);
            }
            return user;
        });
        return result;
    }

    public List<User> findUsers(String username, String note) {
        // sql
        String sql = "SELECT * FROM t_user WHERE user_name LIKE CONCAT('%', ?, '%')" +
                "AND note LIKE CONCAT('%', ?, '%')";

        // params for sql
        Object[] params = new Object[]{username, note};

        List<User> users = jdbcTemplate.query(sql, params, getUserMapper());

        return users;
    }

    public int insertUser(User user) {
        int res = this.jdbcTemplate.execute((Connection conn) -> {
            // sql
            String sql = "INSERT INTO t_user (user_name, sex, note) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setInt(2, user.getSex().getId());
            ps.setString(3, user.getNote());
            return ps.executeUpdate();
        });
        return res;
    }

    public int updateUser(User user) {
        // sql
        String sql = "UPDATE t_user SET user_name = ?, sex = ?, note = ? WHERE id = ?";
        return jdbcTemplate.update(sql, user.getUsername(), user.getSex().getId(), user.getNote(), user.getId());
    }

    public int deleteUser(Long id) {
        // sql
        String sql = "DELETE FROM t_user WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
