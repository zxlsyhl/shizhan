package org.zxl.shizhan.hbase.config;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class HbaseConfig {
    private static Logger logger = LoggerFactory.getLogger(HbaseConfig.class);
    @Bean
    public Admin getAdmin(){
        logger.debug("start config hbase Admin");
        Admin admin = null;
        try {
            org.apache.hadoop.conf.Configuration configuration = HBaseConfiguration.create();
            //注意。这里这行目前没有注释掉的，这行和问题3有关系  是要根据自己zookeeper.znode.parent的配置信息进行修改。
//            configuration.set("zookeeper.znode.parent","/hbase-unsecure"); //与 hbase-site-xml里面的配置信息 zookeeper.znode.parent 一致
            configuration.set("hbase.zookeeper.quorum","192.168.1.102");  //hbase 服务地址
            configuration.set("hbase.zookeeper.property.clientPort","2181"); //端口号
            //这里使用的是接口Admin   该接口有一个实现类HBaseAdmin   也可以直接使用这个实现类
            // HBaseAdmin baseAdmin = new HBaseAdmin(configuration);
            admin = ConnectionFactory.createConnection(configuration).getAdmin();
            if(admin !=null){
                try {
                    //获取到数据库所有表信息
                    HTableDescriptor[] allTable = admin.listTables();
                    for (HTableDescriptor hTableDescriptor : allTable) {
                        System.out.println(hTableDescriptor.getNameAsString());
                        logger.debug(hTableDescriptor.getNameAsString());
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        logger.debug("end config hbase Admin");
        return admin;
    }
}
