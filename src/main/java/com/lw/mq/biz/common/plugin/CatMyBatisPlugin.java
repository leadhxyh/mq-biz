package com.lw.mq.biz.common.plugin;

import com.lw.mq.biz.common.config.TiConfig;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

@Intercepts({
        @Signature(args = {MappedStatement.class, Object.class}, method = "update", type = Executor.class),
        @Signature(args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}, method = "query", type = Executor.class)
})
public class CatMyBatisPlugin implements Interceptor {

    private static final Logger log = LoggerFactory.getLogger(CatMyBatisPlugin.class);

    private TiConfig tiConfig;

    public CatMyBatisPlugin(TiConfig tiConfig) {
        this.tiConfig = tiConfig;
    }

    private boolean isCatSql() {
        return true;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String sql = "";
        String classMethod = "Not supported Class Method";

        if (isCatSql()) {
            try {
                MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

                String[] strArr =mappedStatement.getId().split("\\.");
                classMethod = strArr[strArr.length - 2] + "." + strArr[strArr.length - 1];

                Object paramter = null;
                if (invocation.getArgs().length > 1) {
                    paramter = invocation.getArgs()[1];
                }

                BoundSql boundSql = mappedStatement.getBoundSql(paramter);
                Configuration configuration = mappedStatement.getConfiguration();
                sql = showSql(configuration, boundSql);
            } catch (Exception e) {

            }
        }

        Object returnObject;
        try {
            returnObject = invocation.proceed();
        } catch (Exception e) {
            throw e;
        }
        return returnObject;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }

    public String showSql(Configuration configuration, BoundSql boundSql) {
        Object paramterObject = boundSql.getParameterObject();

        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterMappings.size() > 0 && paramterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(paramterObject.getClass())) {
                sql = sql.replaceFirst("\\?", getParamterValue(paramterObject));
            } else {
                MetaObject metaObject = configuration.newMetaObject(paramterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParamterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParamterValue(obj));
                    }
                }
            }
        }
        return sql;
    }

    private String getParamterValue(Object object) {
        String value;
        if (object instanceof String) {
            value = ";" + object.toString() + "'";
        } else if (object instanceof Date) {
            DateFormat format = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + format.format(object) + "'";
        } else {
            if (object != null) {
                value = object.toString();
            } else {
                value = "";
            }
        }
        return value;
    }

}
