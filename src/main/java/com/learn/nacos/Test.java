package com.learn.nacos;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.io.IOException;
import java.util.Date;
public class Test {
public static void main(String[] args) throws NacosException,InterruptedException,IOException{
        String serverAddr = "localhost";
        String dataId = "appA";
        String group = "DEFAULT_GROUP";
        Properties properties = new Properties();
        properties.put("serverAddr",serverAddr);
        properties.put("namespace","dev");
        ConfigService configService = NacosFactory.createConfigService(properties);
        String content = configService.getConfig(dataId,group,5000);
        System.out.println("first receive:" + content);
        configService.addListener(dataId,group,new Listener(){
                @Override
                public void receiveConfigInfo(String configInfo){
                        System.out.println("currentTime:" + new Date() + ", receive:" +configInfo);
                }
                @Override
                public Executor getExecutor(){return null;}
        });
        int n =System.in.read();
}
}
