package com.wan.springcloudgatewayserver;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Author: WanWenSheng
 * @Description:
 * @Dete: Created in 11:51 2020/12/25
 * @Modified By:
 */
@Component
public class AuthRoutePredicateFactory extends AbstractRoutePredicateFactory<AuthRoutePredicateFactory.Config> {

    public static final String NAME_KEY = "name";
    public static final String REGEXP_KEY = "regexp";

    public AuthRoutePredicateFactory() {
        super(AuthRoutePredicateFactory.Config.class);
    }

    public List<String> shortcutFieldOrder() {
        return Arrays.asList("name","regexp");
    }

    public Predicate<ServerWebExchange> apply(Config config) {
        return (exchange ->{
            HttpHeaders headers=exchange.getRequest().getHeaders();
            List<String> headerList=headers.get(config.getName());
            return headerList.size()>0;
        });
    }

    public static class  Config{

        private String name;


        private String regexp;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRegexp() {
            return regexp;
        }

        public void setRegexp(String regexp) {
            this.regexp = regexp;
        }
    }
}
