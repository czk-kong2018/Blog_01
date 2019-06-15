package com.zhbit.service.interfaces;

import com.zhbit.dto.ArticleToPage;
import com.zhbit.dto.IndexArticle;
import com.zhbit.dto.IndexArticle2;
import com.zhbit.dto.UserArticle;
import com.zhbit.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleService {


    /**
     * 根据文章id查询文章
     */
     Article getArticleByArticleId(int article_id);

    /**
     * 查询用户的所有文章
     */
    List<UserArticle> getALLArticleByUserName(String user_name);
    /**
     * 分页查询用户的文章
     * ＠OnePageCount  一页展示多少文章
     */

    List<ArticleToPage> getArticeByPage(int current_page, int OnePageCount, String user_name);

    /**
     * 获取首页文章
     * @param tag
     * @param num
     * @return
     */
    List<IndexArticle> getIndexArticle(String tag, int num);
    List<IndexArticle2> getIndexArticle2(String tag, int num);
}
