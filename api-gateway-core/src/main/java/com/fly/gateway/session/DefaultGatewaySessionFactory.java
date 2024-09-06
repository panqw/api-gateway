package com.fly.gateway.session;

import com.fly.gateway.datasource.DataSource;
import com.fly.gateway.datasource.unpooled.UnpooledDatasourceFactory;
import com.fly.gateway.executor.Executor;

public class DefaultGatewaySessionFactory implements GatewaySessionFactory {
    private final Configuration configuration;

    public DefaultGatewaySessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public GatewaySession openSession(String uri) {
        UnpooledDatasourceFactory datasourceFactory = new UnpooledDatasourceFactory();
        datasourceFactory.setProperties(configuration,uri);
        DataSource dataSource = datasourceFactory.getDataSource();
        // 创建执行器
        Executor executor = configuration.newExecutor(dataSource.getConnection());
        return new DefaultGatewaySession(configuration, uri, executor);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

}
