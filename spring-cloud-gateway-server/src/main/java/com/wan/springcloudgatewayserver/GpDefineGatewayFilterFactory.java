package com.wan.springcloudgatewayserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: WanWenSheng
 * @Description:
 * @Dete: Created in 14:11 2020/12/25
 * @Modified By:
 */
@Component
public class GpDefineGatewayFilterFactory extends AbstractGatewayFilterFactory<GpDefineGatewayFilterFactory.Config> {

    Logger logger= LoggerFactory.getLogger(GpDefineGatewayFilterFactory.class);

    private static String NAME_KEY="name";

    public GpDefineGatewayFilterFactory() {
        super(GpDefineGatewayFilterFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(NAME_KEY);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) ->{
            logger.info("[pre] Filter Request, name:"+config.getName());
            //TODO
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                //TODO
                logger.info("[post]: Response Filter");
            }));
        } );
    }


    public static class Config{

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
