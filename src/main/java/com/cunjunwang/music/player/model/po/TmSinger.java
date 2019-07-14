package com.cunjunwang.music.player.model.po;

import java.util.Date;

public class TmSinger {
    private Long id;

    private String uuid;

    private String singerName;

    private String singerOtherName;

    private String singerNameIndex;

    private Long thirdId;

    private String thirdUuid;

    private String singerArea;

    private String singerAreaCode;

    private String singerGenre;

    private String singerGenreCode;

    private String singerGender;

    private String singerGenderCode;

    private Integer isDel;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName == null ? null : singerName.trim();
    }

    public String getSingerOtherName() {
        return singerOtherName;
    }

    public void setSingerOtherName(String singerOtherName) {
        this.singerOtherName = singerOtherName == null ? null : singerOtherName.trim();
    }

    public String getSingerNameIndex() {
        return singerNameIndex;
    }

    public void setSingerNameIndex(String singerNameIndex) {
        this.singerNameIndex = singerNameIndex == null ? null : singerNameIndex.trim();
    }

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }

    public String getThirdUuid() {
        return thirdUuid;
    }

    public void setThirdUuid(String thirdUuid) {
        this.thirdUuid = thirdUuid == null ? null : thirdUuid.trim();
    }

    public String getSingerArea() {
        return singerArea;
    }

    public void setSingerArea(String singerArea) {
        this.singerArea = singerArea == null ? null : singerArea.trim();
    }

    public String getSingerAreaCode() {
        return singerAreaCode;
    }

    public void setSingerAreaCode(String singerAreaCode) {
        this.singerAreaCode = singerAreaCode == null ? null : singerAreaCode.trim();
    }

    public String getSingerGenre() {
        return singerGenre;
    }

    public void setSingerGenre(String singerGenre) {
        this.singerGenre = singerGenre == null ? null : singerGenre.trim();
    }

    public String getSingerGenreCode() {
        return singerGenreCode;
    }

    public void setSingerGenreCode(String singerGenreCode) {
        this.singerGenreCode = singerGenreCode == null ? null : singerGenreCode.trim();
    }

    public String getSingerGender() {
        return singerGender;
    }

    public void setSingerGender(String singerGender) {
        this.singerGender = singerGender == null ? null : singerGender.trim();
    }

    public String getSingerGenderCode() {
        return singerGenderCode;
    }

    public void setSingerGenderCode(String singerGenderCode) {
        this.singerGenderCode = singerGenderCode == null ? null : singerGenderCode.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}