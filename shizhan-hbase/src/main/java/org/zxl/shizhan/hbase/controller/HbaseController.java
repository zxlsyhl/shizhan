package org.zxl.shizhan.hbase.controller;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zxl.shizhan.hbase.entity.HbaseCell;
import org.zxl.shizhan.hbase.entity.HbaseRow;
import org.zxl.shizhan.hbase.service.HbaseService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hbase")
public class HbaseController {
    private static Logger logger = LoggerFactory.getLogger(HbaseController.class);
    @Autowired
    private HbaseService hbaseService;

    @GetMapping("/list")
    public List<String> list(){
        logger.debug("list start");
        List<String> tableNames = new ArrayList<>();
        try {
            tableNames = hbaseService.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        logger.debug("list end");
        return tableNames;
    }

    @PostMapping("/createTable")
    public String createTable(@RequestParam String myTable,@RequestParam String familyNamesStr) {
        String result = "9999";
        try {
            logger.debug("createTable start");
            hbaseService.createTable(myTable, familyNamesStr);
            logger.debug("createTable end");
            result = "0000";
        } catch (IOException e) {
            logger.error("createTable fail;{}",e.getMessage());
            e.printStackTrace();
        } finally {
        }
        return result;
    }

    @PostMapping("/addDataRow")
    public int addDataRow(@RequestParam String tableName,@RequestParam  String rowKey,@RequestParam  String family,@RequestParam  String columnsStr,@RequestParam  String valuesStr,@RequestParam(required = false) Long timestamp){
        return hbaseService.addDataRow(tableName, rowKey, family, columnsStr, valuesStr, timestamp);
    }

    @GetMapping("/getRow")
    public List<HbaseCell> getRow(@RequestParam String tableName, @RequestParam(required = false) String familyName, @RequestParam String rowKey, @RequestParam(required = false) String columnsStr){
        List<HbaseCell> hbaseCells = new ArrayList<>();
        try {
            hbaseCells = hbaseService.getRow(tableName, familyName, rowKey, columnsStr);
        } catch (Exception e) {
            logger.error("getRow fail:{}",e.getMessage());
            e.printStackTrace();
        } finally {
        }
        return hbaseCells;
    }

    @DeleteMapping("/deleteDataRows")
    public int deleteDataRows(@RequestParam String tableName, @RequestParam String rowKeysStr){
        return hbaseService.deleteDataRows(tableName, rowKeysStr);
    }

    @GetMapping("/scan")
    public List<HbaseRow> scan(@RequestParam String tableName){
        return hbaseService.scan(tableName);
    }
}
