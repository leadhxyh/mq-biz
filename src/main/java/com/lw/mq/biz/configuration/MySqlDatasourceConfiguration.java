package com.lw.mq.biz.configuration;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.lw.mq.biz.common.config.TiConfig;
import com.lw.mq.biz.common.plugin.CatMyBatisPlugin;
import com.lw.mq.biz.common.plugin.DruidConnectionFilter;
import com.lw.mq.biz.common.util.DbUtil;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@MapperScan(basePackages = MySqlDatasourceConfiguration.BASE_PACKAGE, sqlSessionTemplateRef = "mysqlSessionTemplate")
public class MySqlDatasourceConfiguration {

    /**
     * 接口类文件所在目录
     */
    public static final String BASE_PACKAGE = "com.lw.mq.biz.db";
    /**
     * XML文件所在目录
     */
    public static final String MAPPER_XML_PATH = "classpath:/mapping/Topic.xml";

    @Autowired
    private Environment env;

    @Autowired
    private TiConfig tiConfig;


    @Bean(name = "mysqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mysqlDataSource") DataSource dataSource, TiConfig config)
        throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setPlugins(new Interceptor[] {
            new CatMyBatisPlugin(tiConfig)
        });
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource(MAPPER_XML_PATH));
        bean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return bean.getObject();
    }

    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource mysqlDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();

        List<Filter> filters = new ArrayList<>();
        filters.add(new DruidConnectionFilter(DbUtil.getDbIp(env.getProperty("spring.datasource.url"))));
        druidDataSource.setProxyFilters(filters);

        return druidDataSource;
    }

    @Bean(name = "mysqlTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("mysqlDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mysqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("mysqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
