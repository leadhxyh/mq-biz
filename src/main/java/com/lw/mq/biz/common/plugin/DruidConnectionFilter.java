package com.lw.mq.biz.common.plugin;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;

import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

public class DruidConnectionFilter extends FilterEventAdapter {

    private String ip;

    private int hour = 0;

    public DruidConnectionFilter(String ip) {
        this.ip = ip;
        hour = (new Date()).getHours();
        hour = hour - 3;
    }

    @Override
    public ConnectionProxy connection_connect(FilterChain chain, Properties info) throws SQLException {
        return super.connection_connect(chain, info);
    }

    @Override
    public void connection_close(FilterChain chain, ConnectionProxy connection) throws SQLException {
        super.connection_close(chain, connection);
    }
}
