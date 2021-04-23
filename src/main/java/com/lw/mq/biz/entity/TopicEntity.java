package com.lw.mq.biz.entity;

import java.util.Date;

public class TopicEntity {

    private long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 如果此topic为失败topic，则orgin_name为原始的topic，同时name为consumer_group_name + orgin_name + "_fail"，否则name和orginName一致
     */
    private String orginName;
    /**
     * 部门名称
     */
    private String dptName;
    /**
     * 负责人ids
     */
    private String ownerIds;
    /**
     * 负责人名称
     */
    private String ownerNames;
    /**
     * 延迟告警邮件
     */
    private String emails;
    /**
     * 手机号码集合，可通过配置的号码发送钉钉消息或邮件
     */
    private String tels;
    /**
     * 预期每天的数据量，单位是万
     */
    private int expectDayCount;
    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 消息保存天数
     */
    private int saveDayNum;
    /**
     * 备注
     */
    private String remark;
    /**
     * 如果为空表示不需要验证token，否则消息发送需要匹配token
     */
    private String token;
    /**
     * 所有topic自动分配节点时，全部分配到普通节点上，只有手动分配的时候可以分配到特殊节点。1.表示普通节点，2.表示特殊节点
     */
    private int normalFlag;
    /**
     * 主题类型：1.正常队列，2.失败队列
     */
    private int topicType;
    /**
     * 默认topic 堆积告警条数
     */
    private int maxLag;
    /**
     * 是否允许所有人消费订阅，1是，0否（只能允许消费者组才能订阅此topic消息）
     */
    private int consumerFlag;
    /**
     * 允许订阅消费者组名称列表 逗号分隔
     */
    private String consumerGroupNames;
    /**
     * 操作人
     */
    private String insertBy;
    /**
     * 创建时间
     */
    private Date insertTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 逻辑删除
     */
    private int isActive;
    /**
     * 元数据更新时间
     */
    private Date metaUpdateTime;
    /**
     *
     */
    private String appId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrginName() {
        return orginName;
    }

    public void setOrginName(String orginName) {
        this.orginName = orginName;
    }

    public String getDptName() {
        return dptName;
    }

    public void setDptName(String dptName) {
        this.dptName = dptName;
    }

    public String getOwnerIds() {
        return ownerIds;
    }

    public void setOwnerIds(String ownerIds) {
        this.ownerIds = ownerIds;
    }

    public String getOwnerNames() {
        return ownerNames;
    }

    public void setOwnerNames(String ownerNames) {
        this.ownerNames = ownerNames;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getTels() {
        return tels;
    }

    public void setTels(String tels) {
        this.tels = tels;
    }

    public int getExpectDayCount() {
        return expectDayCount;
    }

    public void setExpectDayCount(int expectDayCount) {
        this.expectDayCount = expectDayCount;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public int getSaveDayNum() {
        return saveDayNum;
    }

    public void setSaveDayNum(int saveDayNum) {
        this.saveDayNum = saveDayNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getNormalFlag() {
        return normalFlag;
    }

    public void setNormalFlag(int normalFlag) {
        this.normalFlag = normalFlag;
    }

    public int getTopicType() {
        return topicType;
    }

    public void setTopicType(int topicType) {
        this.topicType = topicType;
    }

    public int getMaxLag() {
        return maxLag;
    }

    public void setMaxLag(int maxLag) {
        this.maxLag = maxLag;
    }

    public int getConsumerFlag() {
        return consumerFlag;
    }

    public void setConsumerFlag(int consumerFlag) {
        this.consumerFlag = consumerFlag;
    }

    public String getConsumerGroupNames() {
        return consumerGroupNames;
    }

    public void setConsumerGroupNames(String consumerGroupNames) {
        this.consumerGroupNames = consumerGroupNames;
    }

    public String getInsertBy() {
        return insertBy;
    }

    public void setInsertBy(String insertBy) {
        this.insertBy = insertBy;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public Date getMetaUpdateTime() {
        return metaUpdateTime;
    }

    public void setMetaUpdateTime(Date metaUpdateTime) {
        this.metaUpdateTime = metaUpdateTime;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
