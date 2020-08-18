package com.pentas.sellerweb.common.module.mybatis.paginator.dialect;

import org.apache.ibatis.mapping.MappedStatement;

import com.pentas.sellerweb.common.module.mybatis.paginator.domain.PageBounds;

public class SybaseDialect extends Dialect{

    public SybaseDialect(MappedStatement mappedStatement, Object parameterObject, PageBounds pageBounds) {
        super(mappedStatement, parameterObject, pageBounds);
    }


    protected String getLimitString(String sql, String offsetName,int offset, String limitName, int limit) {
		throw new UnsupportedOperationException( "paged queries not supported" );
	}

}
