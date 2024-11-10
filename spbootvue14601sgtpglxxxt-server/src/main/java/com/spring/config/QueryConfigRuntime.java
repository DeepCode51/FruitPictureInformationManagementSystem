package com.spring.config;

import com.jntoo.db.Configuration;
import com.jntoo.db.QueryConfig;
import javax.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 配置数据库访问源
 */
@Component
public class QueryConfigRuntime implements ApplicationRunner {

    @Resource
    private QueryConfigConnect config;

    /**
     * Callback used to run the bean.
     *
     * @param args incoming application arguments
     * @throws Exception on error
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        QueryConfig queryConfig = new QueryConfig();
        queryConfig.setPrefix("");
        queryConfig.setConnectionConfig(config);
        queryConfig.setDebug(true);
        Configuration.setQueryConfig(queryConfig);
    }
}
