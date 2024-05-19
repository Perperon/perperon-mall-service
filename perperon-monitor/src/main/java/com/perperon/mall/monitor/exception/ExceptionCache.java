package com.perperon.mall.monitor.exception;

import org.apache.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.util.*;


/**
 * @author perperon
 * @date 2024/5/19
 * @apiNote
 */
public class ExceptionCache {
    private static final Logger logger = Logger.getLogger(ExceptionCache.class);
    private List<String> locations;
    private static final Map<String, String> cacheProps = new HashMap();

    public ExceptionCache() {
    }

    public void init() throws Exception {
        Iterator var1;
        try {
            var1 = this.locations.iterator();

            while(var1.hasNext()) {
                String url = (String)var1.next();
                Properties props = PropertiesLoaderUtils.loadAllProperties(url);
                Iterator var4 = props.entrySet().iterator();

                while(var4.hasNext()) {
                    Map.Entry<Object, Object> entry = (Map.Entry)var4.next();
                    cacheProps.put(entry.getKey().toString(), entry.getValue().toString());
                }
            }
        } catch (Exception var6) {
            logger.error("error read properties in Cache init, please check property files");
            throw var6;
        }

        var1 = cacheProps.entrySet().iterator();

        while(var1.hasNext()) {
            Map.Entry<String, String> e = (Map.Entry)var1.next();
            logger.debug("get cache property[" + (String)e.getKey() + "=" + (String)e.getValue() + "]");
        }

    }

    public static String getProperty(Object key) {
        return (String)cacheProps.get(key.toString());
    }

    public List<String> getLocations() {
        return this.locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public static Map<String, String> getCacheProps() {
        return cacheProps;
    }
}
