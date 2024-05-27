package com.easy.query.lambda.test.pojo;

import com.easy.query.core.annotation.Column;
import com.easy.query.core.annotation.Table;

import java.time.LocalDateTime;

@Table("t_topic")
public class Topic
{
    @Column(primaryKey = true)
    private String id;
    private Integer stars;
    private String title;
    private LocalDateTime createTime;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Integer getStars()
    {
        return stars;
    }

    public void setStars(Integer stars)
    {
        this.stars = stars;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public LocalDateTime getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime)
    {
        this.createTime = createTime;
    }

    @Override
    public String toString()
    {
        return "Topic{" +
                "id='" + id + '\'' +
                ", stars=" + stars +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
