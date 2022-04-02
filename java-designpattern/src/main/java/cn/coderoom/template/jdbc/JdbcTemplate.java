package cn.coderoom.template.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板模式是指定义一个算法的骨架，并允许子类为一个或者多个步骤提供实现。
 *
 * 以查询为例，每次查询的表都不同(但是查询过程是一致的)，返回的数据结构也就都不一样。
 * 我们针对不同的数据，都要封装成不同的实体对象。而每个实体封装的逻辑都是不一样的，但封装前和封装后的处理流程是不变的，因此，可以使用模板方法模式设计这样的业务场景。
 *  JDBC 模板类中保留了不变的一些基本操作，将封装数据库的逻辑交由子类去实现，这也是模板类在实际业务中的一个运用体现。
 */
public abstract class JdbcTemplate {

    private DataSource dataSource;
    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public final List<?> executeQuery(String sql, RowMapper<?> rowMapper, Object[] values) {
        try {
            //1、获取连接
            Connection conn = this.getConnection();
            //2、创建语句集
            PreparedStatement pstm = this.createPrepareStatement(conn, sql);
            //3、执行语句集
            ResultSet rs = this.executeQuery(pstm, values);
            //4、处理结果集
            List<?> result = this.parseResultSet(rs, rowMapper);
            //5、关闭结果集
            rs.close();
            //6、关闭语句集
            pstm.close();
            //7、关闭连接
            conn.close();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ResultSet executeQuery(PreparedStatement pstm, Object[] values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            pstm.setObject(i, values[i]);
        }
        return pstm.executeQuery();
    }

    private List<?> parseResultSet(ResultSet rs, RowMapper<?> rowMapper) throws Exception {
        List<Object> result = new ArrayList<Object>();
        int rowNum = 0;
        while (rs.next()) {
            result.add(rowMapper.mapRow(rs, rowNum++));
        }
        return result;
    }

    private PreparedStatement createPrepareStatement(Connection conn, String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }
    private Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

}
