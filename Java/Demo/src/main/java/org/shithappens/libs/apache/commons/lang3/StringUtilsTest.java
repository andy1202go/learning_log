/******************************************************************************
 * Copyright (C) 2013 - 2022 ShenZhen OnePlus Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package org.shithappens.libs.apache.commons.lang3;


import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static org.shithappens.libs.TestingSet.FILE_NAME;
import static org.shithappens.libs.TestingSet.NORMAL_LIST;

/**
 * @author liangbo
 * @version V1.0
 * @Title: StringUtilsTest.java
 * @Package org.shithappens.libs.apache.commons.lang3
 * @Description
 * @date 2022 03-03 15:23.
 */
public class StringUtilsTest {
    static Logger log = Logger.getLogger("StringUtilsTest");
    private static final String REDIS_KEY_COMMENT_LIST = "HT:COMMENT:LS";


    public static void main(String[] args) throws Exception {
        Map<String,String> testM = Maps.newConcurrentMap();
        testM.put(null,"1");


        CommentReqDto reqDto = new CommentReqDto();
        reqDto.setPageSize(10);
        reqDto.setCurrentPage(1);
        reqDto.setQueryType(0);
        reqDto.setHasFavorable(0);
        reqDto.setSpuId("P1100022");
        reqDto.setOrderBy(0);
        reqDto.setRegion("in");
        String redisKey = combineKeys(REDIS_KEY_COMMENT_LIST,
                reqDto.getRegion(), reqDto.getSpuId(), reqDto.getSkuId(),
                reqDto.getQueryType(), reqDto.getHasFavorable(), reqDto.getTagList(),
                reqDto.getOrderBy(), "LOCK");

        String list=combineKeys(REDIS_KEY_COMMENT_LIST,
                reqDto.getRegion(), reqDto.getSpuId(), reqDto.getSkuId(),
                reqDto.getQueryType(), reqDto.getHasFavorable(), reqDto.getTagList(),
                reqDto.getOrderBy());

        String co = "indai";
        String c2 = "IN";
        if(!StringUtils.isEmpty(c2)&&c2.length()>2){
            throw new Exception("countryCode length cannot exceed 2");
        }



        List<String> a = new ArrayList<>();
        a.stream().forEach(System.out::println);
        joinTest();

    }

    public static String combineKeys(String key, Object... args) {
        if (args == null || args.length == 0) {
            throw new RuntimeException("combineKeys null");
        }
        StringBuilder sb = new StringBuilder(key);
        for (Object arg : args) {
            if (null != arg) {
                if (arg instanceof String) {
                    if (!StringUtils.isEmpty((String) arg)) {
                        sb.append(":").append(arg.toString());
                    }
                } else {
                    sb.append(":").append(arg.toString());
                }

            }
        }
        return sb.toString();
    }

    private static void joinTest() {
        log.info("null situation is :" + "\n" + StringUtils.join(null));
        log.info("with index is like :" + "\n" + StringUtils.join(NORMAL_LIST, ":", 0, 3));
        log.info("join with null separator:" + "\n" + StringUtils.join(NORMAL_LIST, null));
        log.info("join with null separator function:" + "\n" + StringUtils.join(NORMAL_LIST));
        log.info("join with joinWith" + "\n" + StringUtils.joinWith(";", NORMAL_LIST, FILE_NAME));
    }

    private static class CommentReqDto {

        private int pageSize;

        private int currentPage;

        private int queryType;
        /**
         * 是否用户好评
         */
        private int hasFavorable;

        private String spuId;

        private String skuId;

        private int orderBy;

        private List<Long> tagList;

        private String region;

        private String userId;

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getQueryType() {
            return queryType;
        }

        public void setQueryType(int queryType) {
            this.queryType = queryType;
        }

        public int getHasFavorable() {
            return hasFavorable;
        }

        public void setHasFavorable(int hasFavorable) {
            this.hasFavorable = hasFavorable;
        }

        public String getSpuId() {
            return spuId;
        }

        public void setSpuId(String spuId) {
            this.spuId = spuId;
        }

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public int getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(int orderBy) {
            this.orderBy = orderBy;
        }

        public List<Long> getTagList() {
            return tagList;
        }

        public void setTagList(List<Long> tagList) {
            this.tagList = tagList;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
