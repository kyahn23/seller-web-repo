package com.pentas.sellerweb.common.module.mybatis.paginator.dialect;

import org.apache.ibatis.mapping.MappedStatement;

import com.pentas.sellerweb.common.module.mybatis.paginator.domain.PageBounds;

public class PostgreSQLDialect extends Dialect{

    public PostgreSQLDialect(MappedStatement mappedStatement, Object parameterObject, PageBounds pageBounds) {
        super(mappedStatement, parameterObject, pageBounds);
    }

    protected String getLimitString(String sql, String offsetName,int offset, String limitName, int limit) {
        StringBuffer buffer = new StringBuffer( sql.length()+20 ).append(sql);
        if(offset > 0){
            buffer.append(" limit ? offset ?");
            setPageParameter(limitName, limit, Integer.class);
            setPageParameter(offsetName, offset, Integer.class);

        }else{
            buffer.append(" limit ?");
            setPageParameter(limitName, limit, Integer.class);
        }
		return buffer.toString();
	}
}
