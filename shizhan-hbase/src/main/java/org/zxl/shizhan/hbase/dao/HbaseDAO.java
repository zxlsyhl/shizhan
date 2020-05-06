package org.zxl.shizhan.hbase.dao;

import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class HbaseDAO {
    private static Logger logger = LoggerFactory.getLogger(HbaseDAO.class);
    @Autowired
    private Admin admin;

    private Connection connection = null;

    private void getConnection(){
        if(connection == null){
            connection = admin.getConnection();
        }
    }


    public List<String> list() throws Exception{
        List<String> tablenames = new ArrayList<>();
        //获取到数据库所有表信息
        HTableDescriptor[] allTable = admin.listTables();
        for (HTableDescriptor hTableDescriptor : allTable) {
//            System.out.println(hTableDescriptor.getNameAsString());
            logger.debug(hTableDescriptor.getNameAsString());
            tablenames.add(hTableDescriptor.getNameAsString());
        }
        return tablenames;
    }

    /*
    创建table，指定列族
     */
    public void createTable(String myTable, String[] familyNames) throws IOException {
        if(!admin.isTableAvailable(TableName.valueOf(myTable))){
            TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(TableName.valueOf(myTable));
            for (String familyName: familyNames){
                tableDescriptorBuilder.setColumnFamily(ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(familyName)).build());
            }
            admin.createTable(tableDescriptorBuilder.build());
        }

    }


    /**
     * 添加数据
     *
     * @param tableName 表名
     * @param rowKey    行键
     * @param columns   列族1中的列
     * @param values    列族1中的列的值
     */
    public int addDataRow(String tableName, String rowKey, String family, String[] columns, String[] values ,Long timestamp) {
        try {
            getConnection();
            Table table = connection.getTable(TableName.valueOf(tableName));
            Put put = new Put(Bytes.toBytes(rowKey));
            // 不写wlan日志
            put.setDurability(Durability.SKIP_WAL);
            // 获取表中的所有列族 client version 2.0.1
            ColumnFamilyDescriptor[] columnFamilyDescriptors = table.getDescriptor().getColumnFamilies();
            for (ColumnFamilyDescriptor columnFamilyDescriptor : columnFamilyDescriptors) {
                String familyName = columnFamilyDescriptor.getNameAsString();
                if (familyName.equals(family)) {
                    for (int i = 0; i < columns.length; i++) {
                        // 列族
                        put.addColumn(Bytes.toBytes(familyName),
                                // 列
                                Bytes.toBytes(columns[i]),
                                // 列的值
                                Bytes.toBytes(values[i]));
                    }
                }
            }
            if(timestamp == null){
                timestamp = System.currentTimeMillis();
            }
            put.setTimestamp(timestamp);
            table.put(put);
            return 1;
        } catch (Exception e) {
            logger.error("add row fail:{}",e.getMessage());
            e.printStackTrace();
            return -1;
        }finally {
        }

    }

    /**
     * 查询一行数据
     *
     * @param tableName  表名
     * @param rowKey     索引
     * @param familyName 列簇
     * @param columns    列名
     */
    public Result getRow(String tableName, String familyName, String rowKey, String[] columns) {
        try {
            getConnection();
            Table table = connection.getTable(TableName.valueOf(tableName));
            Get get = new Get(Bytes.toBytes(rowKey));
            //指定列
            if (null != columns && columns.length > 0) {
                for (String column : columns) {
                    get.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(column));
                }
            }
            return table.get(get);
        } catch (Exception e) {
            logger.error("getRow fail:{}",e.getMessage() );
            e.printStackTrace();
        }finally {
//            try {
//                if (connection!=null){
//                    connection.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        return null;
    }

    /**
     * 删除一行或多行数据
     */
    public int deleteDataRows(String tableName, String[] rowKeys) {
        try {
            getConnection();
            Table table = connection.getTable(TableName.valueOf(tableName));
            List<Delete> deleteList = new ArrayList<>(rowKeys.length);
            Delete delete;
            for (String rowKey : rowKeys) {
                delete = new Delete(Bytes.toBytes(rowKey));
                deleteList.add(delete);
            }
            table.delete(deleteList);
            return 1;
        } catch (Exception e) {
            logger.error("deleteDataRows fail:{}",e.getMessage() );
            e.printStackTrace();
            return -1;
        }finally {

        }
    }

    public ResultScanner scan(String tableName){
//        Connection connection = null;
        try {
//            connection = admin.getConnection();
            getConnection();
            Table table = connection.getTable(TableName.valueOf(tableName));
            return table.getScanner(new Scan());
        } catch (Exception e) {
            logger.error("deleteDataRows fail:{}",e.getMessage() );
            e.printStackTrace();
            return null;
        }finally {
//            try {
//                if (connection!=null){
//                    connection.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

}
