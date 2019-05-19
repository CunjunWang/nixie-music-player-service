package com.cunjunwang.music.player.model.po;

import java.util.Date;

public class TmSingerTags {
    private Long id;

    private String tagName;

    private String tagEnglishName;

    private Integer tagCode;

    private Integer isDel;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    public String getTagEnglishName() {
        return tagEnglishName;
    }

    public void setTagEnglishName(String tagEnglishName) {
        this.tagEnglishName = tagEnglishName == null ? null : tagEnglishName.trim();
    }

    public Integer getTagCode() {
        return tagCode;
    }

    public void setTagCode(Integer tagCode) {
        this.tagCode = tagCode;
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