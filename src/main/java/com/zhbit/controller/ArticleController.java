package com.zhbit.controller;


import com.zhbit.dto.ArticleIdAndUserName;
import com.zhbit.dto.ArticleToPage;
import com.zhbit.dto.IndexArticle;
import com.zhbit.dto.IndexArticle2;
import com.zhbit.entity.Article;
import com.zhbit.service.interfaces.ArticleService;
import com.zhbit.service.interfaces.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

@RequestMapping("/article")
@CrossOrigin(origins="*")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserMessageService userMessageService;

    /**
     *
     * 根据文章id获取文章
     * @param articleIdAndUserName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getByArticleId",method = RequestMethod.POST)
    public Article getArticleByArticleId(@RequestBody ArticleIdAndUserName articleIdAndUserName){
        System.out.println(articleIdAndUserName);
        Article article = articleService.getArticleByArticleId(
                Integer.parseInt(articleIdAndUserName.getArticle_id()));
        return  article;
    }

    /**
     * 文章分页获取文章
     * @param user_name
     * @param current_page
     * @param OnePageCount
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{user_name}/getArticleToPage",method = RequestMethod.POST)
    public List<ArticleToPage> getArticleToPage(@PathVariable("user_name") String user_name, String current_page, String OnePageCount){

        List<ArticleToPage> articleList = articleService.getArticeByPage(Integer.parseInt(current_page), Integer.parseInt(OnePageCount), user_name);
        return articleList;


    }

    /**
     * 根据url参数获取aritcle.html
     * @param article_id
     * @param user_name
     * @return
     */

    @RequestMapping("/{article_id}/{user_name}")
    public String getArticleHtml(@PathVariable("article_id")int article_id,@PathVariable("user_name")String user_name){
        Article article = articleService.getArticleByArticleId(article_id);
        int userId = userMessageService.getUserId(user_name);

        return article!=null&&article.getOwn_id()==userId? "front/article" : "front/404";
    }

    /**
     * TODO  提交文章
     */
    @RequestMapping("/postArticle")
    public void PostArticle(){

    }

    @RequestMapping("/getIndexArticle")
    @ResponseBody
    public List<IndexArticle> getIndexArticle(String tag,int num){
        List<IndexArticle> indexArticles=articleService.getIndexArticle(tag,num);
        System.out.println("1: "+indexArticles.size());
        if(indexArticles!=null){
            for(int i=0;i<indexArticles.size();i++){
                System.out.println(indexArticles.get(i).getArticle().getTitle());
            }
        }
        return indexArticles;
    }


    @RequestMapping("/getIndexArticle2")
    @ResponseBody
    public List<IndexArticle2> getIndexArticle2(String tag,Integer num){
        List<IndexArticle2> indexArticles=articleService.getIndexArticle2(tag,num);
        System.out.println("size: "+indexArticles.size());
        if(indexArticles!=null){
            for(int i=0;i<indexArticles.size();i++){
                System.out.println("name: "+indexArticles.get(i).getUserMessage().getUser_name());
            }
        }
        return indexArticles;
    }

}
