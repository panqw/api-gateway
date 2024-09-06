package com.fly.gateway.datasource.unpooled;

import com.fly.gateway.datasource.DataSource;
import com.fly.gateway.datasource.DataSourceFactory;
import com.fly.gateway.datasource.DataSourceType;
import com.fly.gateway.session.Configuration;

public class UnpooledDatasourceFactory implements DataSourceFactory {


    protected UnpooledDatasource dataSource;

    public UnpooledDatasourceFactory() {
        this.dataSource = new UnpooledDatasource();
    }
    @Override
    public void setProperties(Configuration configuration, String uri) {
        this.dataSource.setConfiguration(configuration);
        this.dataSource.setDataSourceType(DataSourceType.Dubbo);
        this.dataSource.setHttpStatement(configuration.getHttpStatement(uri));
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }
}
