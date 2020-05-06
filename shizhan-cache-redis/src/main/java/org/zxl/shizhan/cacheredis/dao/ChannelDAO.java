package org.zxl.shizhan.cacheredis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.zxl.shizhan.cacheredis.entity.TChannel;
import org.zxl.shizhan.cacheredis.util.PageInfo;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChannelDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<TChannel> getChannels(){
        return jdbcTemplate.query("select * from t_channel" , new BeanPropertyRowMapper(TChannel.class) );
    }

    public long totalnum(String tableName, String sqlWhere,  Object[] objects){
        String sql = "select count(*) from "+tableName + sqlWhere;
        Long num = jdbcTemplate.queryForObject(sql, objects ,Long.class);
        return num==null?0:num;
    }


    public PageInfo<TChannel> query(TChannel tChannel, PageInfo<TChannel> pageInfo){
        System.out.println("query dao start");
        StringBuffer sql = new StringBuffer("select * from t_channel ");
        StringBuffer sqlWhere = new StringBuffer(" where 1=1");
        List<Object> paraList = new ArrayList<>();
        if(tChannel != null){
            if(tChannel.getChannelId()!=0){
                sqlWhere.append(" and channel_id = ? ");
                paraList.add(tChannel.getChannelId());
            }
            if(!StringUtils.isEmpty(tChannel.getChannelName()) ){
                sqlWhere.append(" and channel_name = ? ");
                paraList.add(tChannel.getChannelName());
            }
        }

        long totalnum = totalnum("t_channel", sqlWhere.toString() ,paraList.toArray());
        pageInfo.setTotalNum(totalnum);
        pageInfo.setTotalPage(totalnum/pageInfo.getPageSize());
        StringBuffer sqlPage = new StringBuffer();
        if(pageInfo.getPageNo()!=0 && pageInfo.getPageSize()!=0){
            long start = (pageInfo.getPageNo()-1) * pageInfo.getPageSize();
            sqlPage.append(" limit ? , ?");
            paraList.add(start);
            paraList.add(pageInfo.getPageSize());
        }else
        {
            sqlPage.append(" limit ? , ?");
            paraList.add(0);
            paraList.add(pageInfo.getPageSize());
        }

        List<TChannel> tChannels = jdbcTemplate.query(sql.append(sqlWhere.toString()).append(sqlPage).toString(), paraList.toArray() , new BeanPropertyRowMapper(TChannel.class) );
        pageInfo.setObjList(tChannels);
        return pageInfo;

    }

    public void add(TChannel tChannel){
        String sql = "insert into t_channel values(?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{tChannel.getChannelId(),tChannel.getChannelName(),tChannel.getCreateDate(),tChannel.getUpdateDate()});
    }
}
