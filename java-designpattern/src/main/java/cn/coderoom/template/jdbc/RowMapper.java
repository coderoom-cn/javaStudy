package cn.coderoom.template.jdbc;

import java.sql.ResultSet;

/**
 * 建约束 ORM 逻辑的接口 RowMapper。
 * @param <T>
 */
public interface RowMapper <T> {

    /**
     * 用于实现实体关系映射
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws Exception
     */
    public T mapRow(ResultSet rs, int rowNum) throws Exception;

}