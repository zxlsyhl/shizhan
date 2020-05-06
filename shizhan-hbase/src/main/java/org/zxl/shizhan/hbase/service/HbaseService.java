package org.zxl.shizhan.hbase.service;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zxl.shizhan.hbase.dao.HbaseDAO;
import org.zxl.shizhan.hbase.entity.HbaseCell;
import org.zxl.shizhan.hbase.entity.HbaseRow;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class HbaseService {
    private static Logger logger = LoggerFactory.getLogger(HbaseService.class);
    @Autowired
    public HbaseDAO hbaseDAO;

    public List<String> list() throws Exception{
        return hbaseDAO.list();
    }

    public void createTable(String myTable, String familyNamesStr) throws IOException {
        String[] familyNames = familyNamesStr.split("\\|");
        hbaseDAO.createTable(myTable, familyNames);
    }


    public int addDataRow(String tableName, String rowKey, String family, String columnsStr, String valuesStr,Long timestamp){
        String[] columns = columnsStr.split("\\|");
        String[] values = valuesStr.split("\\|");
        return hbaseDAO.addDataRow(tableName, rowKey, family, columns, values,timestamp);
    }

    public List<HbaseCell> getCells(Result result){
        List<HbaseCell> hbaseCells = new ArrayList<>();
        try {
            HbaseCell hbaseCell = null;
            List<Cell> cells = result.listCells();
            for (Cell cell: cells){
                byte[] bytes = cell.getRowArray();
                logger.debug("Row：length:{};array:{};offset:{};result:{}；tostring:{}",cell.getRowLength(),cell.getRowArray(),cell.getRowOffset(), Arrays.copyOfRange(bytes,cell.getRowOffset(),cell.getRowOffset()+cell.getRowLength()),new String( Arrays.copyOfRange(bytes,cell.getRowOffset(),cell.getRowOffset()+cell.getRowLength()), "UTF-8"));
                logger.debug("Family：length:{};array:{};offset:{};result:{}",cell.getFamilyOffset(),cell.getFamilyArray(),cell.getFamilyOffset(), Arrays.copyOfRange(bytes,cell.getFamilyOffset(),cell.getFamilyOffset()+cell.getFamilyOffset()));
                logger.debug("Qualifier：length:{};array:{};offset:{};result:{}",cell.getQualifierLength(),cell.getQualifierArray(),cell.getQualifierOffset(), Arrays.copyOfRange(bytes,cell.getQualifierOffset(),cell.getQualifierOffset()+cell.getQualifierLength()));
                logger.debug("Value：length:{};array:{};offset:{};result:{}",cell.getValueLength(),cell.getValueArray(),cell.getValueOffset(), Arrays.copyOfRange(bytes,cell.getValueOffset(),cell.getValueOffset()+cell.getValueLength()));
                String row = new String( Arrays.copyOfRange(bytes,cell.getRowOffset(),cell.getRowOffset()+cell.getRowLength()), "UTF-8");// cell.getRowArray().toString();
                String family = new String( Arrays.copyOfRange(bytes,cell.getFamilyOffset(),cell.getFamilyOffset()+cell.getFamilyLength()), "UTF-8");// new String(cell.getFamilyArray(), "UTF-8");// Bytes.toString(cell.getFamilyArray());;
                String columnName = new String( Arrays.copyOfRange(bytes,cell.getQualifierOffset(),cell.getQualifierOffset()+cell.getQualifierLength()), "UTF-8");//Bytes.toString(cell.getQualifierArray());;
                long timestamp = cell.getTimestamp();;
                String value = new String( Arrays.copyOfRange(bytes,cell.getValueOffset(),cell.getValueOffset()+cell.getValueLength()), "UTF-8");//Bytes.toString(cell.getValueArray());;
                hbaseCell = new HbaseCell(row,family,columnName,timestamp,value);
                hbaseCells.add(hbaseCell);
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("getCells fail:{}",e.getMessage());
            e.printStackTrace();
        } finally {
        }
        return hbaseCells;
    }

    public List<HbaseCell> getRow(String tableName, String familyName, String rowKey, String columnsStr) {
        List<HbaseCell> hbaseCells = new ArrayList<>();
        Result result = null;
        if(StringUtils.isNotBlank(columnsStr)){
            String[] columns = columnsStr.split("\\|");
            result = hbaseDAO.getRow(tableName, familyName, rowKey, columns);
        }else{
            result = hbaseDAO.getRow(tableName, familyName, rowKey, null);
        }
        hbaseCells = getCells(result);
//        HbaseCell hbaseCell = null;
//                List<Cell> cells = result.listCells();
//        for (Cell cell: cells){
//            byte[] bytes = cell.getRowArray();
//            logger.debug("Row：length:{};array:{};offset:{};result:{}；tostring:{}",cell.getRowLength(),cell.getRowArray(),cell.getRowOffset(), Arrays.copyOfRange(bytes,cell.getRowOffset(),cell.getRowOffset()+cell.getRowLength()),new String( Arrays.copyOfRange(bytes,cell.getRowOffset(),cell.getRowOffset()+cell.getRowLength()), "UTF-8"));
//            logger.debug("Family：length:{};array:{};offset:{};result:{}",cell.getFamilyOffset(),cell.getFamilyArray(),cell.getFamilyOffset(), Arrays.copyOfRange(bytes,cell.getFamilyOffset(),cell.getFamilyOffset()+cell.getFamilyOffset()));
//            logger.debug("Qualifier：length:{};array:{};offset:{};result:{}",cell.getQualifierLength(),cell.getQualifierArray(),cell.getQualifierOffset(), Arrays.copyOfRange(bytes,cell.getQualifierOffset(),cell.getQualifierOffset()+cell.getQualifierLength()));
//            logger.debug("Value：length:{};array:{};offset:{};result:{}",cell.getValueLength(),cell.getValueArray(),cell.getValueOffset(), Arrays.copyOfRange(bytes,cell.getValueOffset(),cell.getValueOffset()+cell.getValueLength()));
//            String row = new String( Arrays.copyOfRange(bytes,cell.getRowOffset(),cell.getRowOffset()+cell.getRowLength()), "UTF-8");// cell.getRowArray().toString();
//            String family = new String( Arrays.copyOfRange(bytes,cell.getFamilyOffset(),cell.getFamilyOffset()+cell.getFamilyLength()), "UTF-8");// new String(cell.getFamilyArray(), "UTF-8");// Bytes.toString(cell.getFamilyArray());;
//            String columnName = new String( Arrays.copyOfRange(bytes,cell.getQualifierOffset(),cell.getQualifierOffset()+cell.getQualifierLength()), "UTF-8");//Bytes.toString(cell.getQualifierArray());;
//            long timestamp = cell.getTimestamp();;
//            String value = new String( Arrays.copyOfRange(bytes,cell.getValueOffset(),cell.getValueOffset()+cell.getValueLength()), "UTF-8");//Bytes.toString(cell.getValueArray());;
//            hbaseCell = new HbaseCell(row,family,columnName,timestamp,value);
//            hbaseCells.add(hbaseCell);
//        }
        return hbaseCells;
    }


    public int deleteDataRows(String tableName, String rowKeysStr){
        String[] rowKeys = rowKeysStr.split("\\|");
        return hbaseDAO.deleteDataRows(tableName, rowKeys);
    }

    public List<HbaseRow> scan(String tableName){
        List<HbaseRow> hbaseRows = new ArrayList<>();
        try {
            ResultScanner resultScanner = hbaseDAO.scan(tableName);
            HbaseRow hbaseRow = null;
            Iterator<Result> resultIterator = resultScanner.iterator();
            while (resultIterator.hasNext()){
                Result result = resultIterator.next();
                String rowKey = new String(result.getRow(), "UTF-8") ;
                List<HbaseCell> hbaseCells = getCells(result);
                hbaseRow = new HbaseRow(rowKey,hbaseCells);
                hbaseRows.add(hbaseRow);
            }
        } catch (Exception e) {
            logger.error("scan fail:{}" ,e.getMessage());
            e.printStackTrace();
        } finally {
        }
        return hbaseRows;
    }
}
