package com.pentas.sellerweb.common.module.mybatis.paginator.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.transaction.Transaction;

import com.pentas.sellerweb.common.module.mybatis.paginator.dialect.Dialect;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SQLHelp {

    /**
     * 총 레코드 수
     *
     * @param mappedStatement mapped
     * @param parameterObject 매개 변수
     * @param boundSql        boundSql
     * @param dialect         database dialect
     * @return 총 레코드 수
     * @throws java.sql.SQLException sql검색오류
     */
    public static int getCount(
                               final MappedStatement mappedStatement, final Transaction transaction, final Object parameterObject,
                               final BoundSql boundSql, Dialect dialect) throws SQLException {
        final String count_sql = dialect.getCountSQL();
        log.debug("Total count SQL [{}] ", count_sql);
        log.debug("Total count Parameters: {} ", parameterObject);

        Connection connection = transaction.getConnection();
        PreparedStatement countStmt = connection.prepareStatement(count_sql);
        DefaultParameterHandler handler = new DefaultParameterHandler(mappedStatement,parameterObject,boundSql);
        handler.setParameters(countStmt);

        ResultSet rs = countStmt.executeQuery();
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        log.debug("Total count: {}", count);
        return count;

    }

}