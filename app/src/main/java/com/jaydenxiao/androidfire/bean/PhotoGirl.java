/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.jaydenxiao.androidfire.bean;

import java.util.List;

/**
 * {
 *     "_id": "5e958fe40bd5529b54e712a6",
 *     "author": "鸢媛",
 *     "category": "Girl",
 *     "createdAt": "2020-04-30 08:00:00",
 *     "desc": "其实也不是什么忘不掉的人\n只是对那场无结果的付出和被浪费的炽热的爱\n耿耿于怀罢了",
 *     "images": [
 *         "http://gank.io/images/291ee91a554f4cd88bf9a09a9fa2e95e"
 *     ],
 *     "likeCounts": 0,
 *     "publishedAt": "2020-04-30 08:00:00",
 *     "stars": 1,
 *     "title": "第71期",
 *     "type": "Girl",
 *     "url": "http://gank.io/images/291ee91a554f4cd88bf9a09a9fa2e95e",
 *     "views": 43
 * }
 */
public class PhotoGirl {

    private String _id;
    private String author;
    private String category;
    private String createdAt;
    private String desc;
    private List<String> images;
    private Integer likeCounts;
    private String publishedAt;
    private Integer stars;
    private String title;
    private String type;
    private String url;
    private Integer views;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Integer getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(Integer likeCounts) {
        this.likeCounts = likeCounts;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }
}
