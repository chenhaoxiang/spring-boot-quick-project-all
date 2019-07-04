package ${project.new-base-package}.entity;

import com.uifuture.common.BaseEntity;
import java.util.Date;

/**
 * 博客信息
 * table name: blogs
 * @author chenhx 2019-07-04
 */
public class Blogs extends BaseEntity {
    /**
     * 
     * fields name: blogs.id
     */
    private Integer id;

    /**
     * 标题
     * fields name: blogs.title
     */
    private String title;

    /**
     * 博客的索引- 创建规则就是博客发布的时间，例如201807201439568+15位随机数
     * fields name: blogs.blog_token
     */
    private String blogToken;

    /**
     * 作者
     * fields name: blogs.author
     */
    private String author;

    /**
     * 摘要
     * fields name: blogs.summary
     */
    private String summary;

    /**
     * 原文链接
     * fields name: blogs.original_url
     */
    private String originalUrl;

    /**
     * 原文发表时间
     * fields name: blogs.publish_time
     */
    private Date publishTime;

    /**
     * 创建时间
     * fields name: blogs.create_time
     */
    private Date createTime;

    /**
     * 修改时间
     * fields name: blogs.update_time
     */
    private Date updateTime;

    /**
     * 状态 0-正常 1-删除 2-待审核
     * fields name: blogs.status
     */
    private Byte status;

    /**
     * 被顶数
     * fields name: blogs.top_count
     */
    private Integer topCount;

    /**
     * 浏览数
     * fields name: blogs.view_count
     */
    private Integer viewCount;

    /**
     * 评论数
     * fields name: blogs.comment_count
     */
    private Integer commentCount;

    /**
     * 类别名称
     * fields name: blogs.category_name_en
     */
    private String categoryNameEn;

    /**
     * 创建者 - 也就是作者
     * fields name: blogs.create_id
     */
    private Integer createId;

    /**
     * 修改者 - 不一定是作者，可能是管理员
     * fields name: blogs.update_id
     */
    private Integer updateId;

    /**
     * 当前的版本
     * fields name: blogs.version
     */
    private Integer version;

    /**
     * 
     * fields name: blogs.ext1
     */
    private String ext1;

    /**
     * 
     * fields name: blogs.ext2
     */
    private String ext2;

    /**
     * 
     * fields name: blogs.ext3
     */
    private String ext3;

    /**
     * 被收藏数
     * fields name: blogs.collect_count
     */
    private Integer collectCount;

    /**
     * 原创标志 0-转载 1-原创 2-翻译
     * fields name: blogs.original
     */
    private Byte original;

    /**
     * 平台英文名称
     * fields name: blogs.platform_name_en
     */
    private String platformNameEn;

    /**
     * 0-页面展示md格式，1-页面展示html
     * fields name: blogs.look_status
     */
    private Byte lookStatus;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 标题
     * @return title 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 博客的索引- 创建规则就是博客发布的时间，例如201807201439568+15位随机数
     * @return blog_token 博客的索引- 创建规则就是博客发布的时间，例如201807201439568+15位随机数
     */
    public String getBlogToken() {
        return blogToken;
    }

    /**
     * 博客的索引- 创建规则就是博客发布的时间，例如201807201439568+15位随机数
     * @param blogToken 博客的索引- 创建规则就是博客发布的时间，例如201807201439568+15位随机数
     */
    public void setBlogToken(String blogToken) {
        this.blogToken = blogToken == null ? null : blogToken.trim();
    }

    /**
     * 作者
     * @return author 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 作者
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    /**
     * 摘要
     * @return summary 摘要
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 摘要
     * @param summary 摘要
     */
    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    /**
     * 原文链接
     * @return original_url 原文链接
     */
    public String getOriginalUrl() {
        return originalUrl;
    }

    /**
     * 原文链接
     * @param originalUrl 原文链接
     */
    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl == null ? null : originalUrl.trim();
    }

    /**
     * 原文发表时间
     * @return publish_time 原文发表时间
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * 原文发表时间
     * @param publishTime 原文发表时间
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     * @return update_time 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 状态 0-正常 1-删除 2-待审核
     * @return status 状态 0-正常 1-删除 2-待审核
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 状态 0-正常 1-删除 2-待审核
     * @param status 状态 0-正常 1-删除 2-待审核
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 被顶数
     * @return top_count 被顶数
     */
    public Integer getTopCount() {
        return topCount;
    }

    /**
     * 被顶数
     * @param topCount 被顶数
     */
    public void setTopCount(Integer topCount) {
        this.topCount = topCount;
    }

    /**
     * 浏览数
     * @return view_count 浏览数
     */
    public Integer getViewCount() {
        return viewCount;
    }

    /**
     * 浏览数
     * @param viewCount 浏览数
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * 评论数
     * @return comment_count 评论数
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * 评论数
     * @param commentCount 评论数
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * 类别名称
     * @return category_name_en 类别名称
     */
    public String getCategoryNameEn() {
        return categoryNameEn;
    }

    /**
     * 类别名称
     * @param categoryNameEn 类别名称
     */
    public void setCategoryNameEn(String categoryNameEn) {
        this.categoryNameEn = categoryNameEn == null ? null : categoryNameEn.trim();
    }

    /**
     * 创建者 - 也就是作者
     * @return create_id 创建者 - 也就是作者
     */
    public Integer getCreateId() {
        return createId;
    }

    /**
     * 创建者 - 也就是作者
     * @param createId 创建者 - 也就是作者
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
     * 修改者 - 不一定是作者，可能是管理员
     * @return update_id 修改者 - 不一定是作者，可能是管理员
     */
    public Integer getUpdateId() {
        return updateId;
    }

    /**
     * 修改者 - 不一定是作者，可能是管理员
     * @param updateId 修改者 - 不一定是作者，可能是管理员
     */
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    /**
     * 当前的版本
     * @return version 当前的版本
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 当前的版本
     * @param version 当前的版本
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 
     * @return ext1 
     */
    public String getExt1() {
        return ext1;
    }

    /**
     * 
     * @param ext1 
     */
    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    /**
     * 
     * @return ext2 
     */
    public String getExt2() {
        return ext2;
    }

    /**
     * 
     * @param ext2 
     */
    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    /**
     * 
     * @return ext3 
     */
    public String getExt3() {
        return ext3;
    }

    /**
     * 
     * @param ext3 
     */
    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }

    /**
     * 被收藏数
     * @return collect_count 被收藏数
     */
    public Integer getCollectCount() {
        return collectCount;
    }

    /**
     * 被收藏数
     * @param collectCount 被收藏数
     */
    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    /**
     * 原创标志 0-转载 1-原创 2-翻译
     * @return original 原创标志 0-转载 1-原创 2-翻译
     */
    public Byte getOriginal() {
        return original;
    }

    /**
     * 原创标志 0-转载 1-原创 2-翻译
     * @param original 原创标志 0-转载 1-原创 2-翻译
     */
    public void setOriginal(Byte original) {
        this.original = original;
    }

    /**
     * 平台英文名称
     * @return platform_name_en 平台英文名称
     */
    public String getPlatformNameEn() {
        return platformNameEn;
    }

    /**
     * 平台英文名称
     * @param platformNameEn 平台英文名称
     */
    public void setPlatformNameEn(String platformNameEn) {
        this.platformNameEn = platformNameEn == null ? null : platformNameEn.trim();
    }

    /**
     * 0-页面展示md格式，1-页面展示html
     * @return look_status 0-页面展示md格式，1-页面展示html
     */
    public Byte getLookStatus() {
        return lookStatus;
    }

    /**
     * 0-页面展示md格式，1-页面展示html
     * @param lookStatus 0-页面展示md格式，1-页面展示html
     */
    public void setLookStatus(Byte lookStatus) {
        this.lookStatus = lookStatus;
    }
}