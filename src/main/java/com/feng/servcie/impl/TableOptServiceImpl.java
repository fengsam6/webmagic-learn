package com.feng.servcie.impl;

import com.feng.servcie.TableOptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TableOptServiceImpl implements TableOptService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void cleanTableData(String tableName) {
        if (StringUtils.isEmpty(tableName)) {
            throw new RuntimeException("tableName 不能为空");
        }

        String sql = "truncate table  " + tableName;
        if (log.isDebugEnabled()) {
            log.debug("成功删除表{}",tableName);
        }
        jdbcTemplate.update(sql);
    }
}
