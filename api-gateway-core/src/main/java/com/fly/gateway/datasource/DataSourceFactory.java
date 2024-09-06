package com.fly.gateway.datasource;

import com.fly.gateway.session.Configuration;

public interface DataSourceFactory {

    void setProperties(Configuration configuration, String uri);

    DataSource getDataSource();
}
