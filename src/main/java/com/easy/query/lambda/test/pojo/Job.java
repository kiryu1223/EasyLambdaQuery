package com.easy.query.lambda.test.pojo;

import com.easy.query.core.annotation.Table;

@Table
public class Job
{
    private int userId;
    private String jobName;

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getJobName()
    {
        return jobName;
    }

    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }
}
