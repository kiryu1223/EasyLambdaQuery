package com.easy.query.lambda.test.pojo;

import com.easy.query.core.annotation.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Table
public class Blog
{
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 博客链接
     */
    private String url;
    /**
     * 点赞数
     */
    private Integer stars;
    /**
     * 发布时间
     */
    private LocalDateTime publishTime;
    /**
     * 评分
     */
    private BigDecimal score;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 排序
     */
    private BigDecimal order;

    /**
     * 是否置顶
     */
    //private Boolean isTop;

    /**
     * 是否置顶
     */
    //private Boolean top;

    private List<Job> jobs;

    public List<Job> getJobs()
    {
        return jobs;
    }

    public void setJobs(List<Job> jobs)
    {
        this.jobs = jobs;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public Integer getStars()
    {
        return stars;
    }

    public void setStars(Integer stars)
    {
        this.stars = stars;
    }

    public LocalDateTime getPublishTime()
    {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime)
    {
        this.publishTime = publishTime;
    }

    public BigDecimal getScore()
    {
        return score;
    }

    public void setScore(BigDecimal score)
    {
        this.score = score;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public BigDecimal getOrder()
    {
        return order;
    }

    public void setOrder(BigDecimal order)
    {
        this.order = order;
    }

//    public Boolean getTop()
//    {
//        return isTop;
//    }
//
//    public void setTop(Boolean top)
//    {
//        isTop = top;
//    }


    @Override
    public String toString()
    {
        return "Blog{" +
                "content='" + content + '\'' +
                ", stars=" + stars +
                '}';
    }
}
