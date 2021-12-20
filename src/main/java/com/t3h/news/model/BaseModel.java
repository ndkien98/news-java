package com.t3h.news.model;


import lombok.Data;

import java.sql.Timestamp;
@Data
public abstract class BaseModel {
    private int id;

    private Timestamp createDate;

    private Timestamp updateDate;

    private int creatorId;

    private int editorId;


}
