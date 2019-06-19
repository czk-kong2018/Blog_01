package com.zhbit.controller;


import com.zhbit.dao.TagDao;
import com.zhbit.dto.ArticleToPage;
import com.zhbit.dto.IndexArticle;
import com.zhbit.dto.IndexArticle2;
import com.zhbit.dto.PublishArticle;
import com.zhbit.entity.Article;
import com.zhbit.entity.UserMessage;
import com.zhbit.enums.ArticleEnum;
import com.zhbit.service.interfaces.ArticleService;
import com.zhbit.service.interfaces.TagService;
import com.zhbit.service.interfaces.UploadService;
import com.zhbit.service.interfaces.UserMessageService;
import com.zhbit.util.JsonUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller

@RequestMapping("/article")
@CrossOrigin(origins="*")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserMessageService userMessageService;

    @Autowired
    private TagService tagService;

    @Autowired
    UploadService up;
    /**
     *
     * 根据文章id获取文章
     * @param article_id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getByArticleId",method = RequestMethod.POST)
    public Article getArticleByArticleId(String article_id){
        System.out.println(article_id);
        Article article = articleService.getArticleByArticleId(
                Integer.parseInt(article_id));
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
        System.out.println("articlePage");
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
    @RequestMapping("/publish")
    @ResponseBody
    public void PostArticle(@RequestBody PublishArticle publishArticle, HttpServletResponse response) throws IOException {
        //文章作者的id
        up.publish(publishArticle,"/usr/source/markdown/");
        // TODO 返回插入成功
        JsonUtils.CreateJsonAndSend(response, ArticleEnum.SUCCESSPUBLISH.toMap());

    }

    /**
     * 上传文章所需的图片,需要先将用户id传过来
     */
    @ResponseBody
    @RequestMapping(value = "/uploadImg",produces = "text/html;charset=utf8")
    public String uploadImg(@RequestParam(value="editormd-image-file") MultipartFile file,HttpSession httpSession){
        //从session中获取用户信息
       // UserMessage user =(UserMessage) httpSession.getAttribute("user");
        UserMessage user =(UserMessage) httpSession.getAttribute("user");
        if(user==null){

            return JsonUtils.getImgUrl(false,null,"请先登录");
        }
        return up.uploadImg(String.valueOf(user.getUser_id()),file,"/usr/source/image/");
    }
    /**
     * 根据用户名获取用户所有文章  临时使用在后台管理文章处  未分页
     */
    @ResponseBody
    @RequestMapping("/getAllArticlesById")
    public List<Article> getAllArticleByUserId(int user_id){
        List<Article> list = articleService.getALLArticleByUserId(user_id);
        return list;
    }

    /**
     * 首页方面的文章操作
     * @Author 拔锋
     */
    @RequestMapping("/getIndexArticle")
    @ResponseBody
    public List<IndexArticle> getIndexArticle(String tag, int num){
        List<IndexArticle> indexArticles=articleService.getIndexArticle(tag,num);
//        System.out.println("1: "+indexArticles.size());
//        if(indexArticles!=null){
//            for(int i=0;i<indexArticles.size();i++){
//                System.out.println(indexArticles.get(i).getArticle().getTitle());
//            }
//        }
        return indexArticles;
    }


    @RequestMapping("/getIndexArticle2")
    @ResponseBody
    public List<IndexArticle2> getIndexArticle2(String tag, Integer num){
        List<IndexArticle2> indexArticles=articleService.getIndexArticle2(tag,num);
//        System.out.println("size: "+indexArticles.size());
//        if(indexArticles!=null){
//            for(int i=0;i<indexArticles.size();i++){
//                System.out.println("name: "+indexArticles.get(i).getUserMessage().getUser_name());
//            }
//        }
        return indexArticles;
    }


    /**
     * 后台管理所需
     * @Author
     */
/*
    @ResponseBody
    @RequestMapping("/editor")
    public String editor(@RequestParam(value="articleID",required=false)Integer articleID,HttpSession session){
        System.out.println("editor: "+articleID);
        if(articleID!=null)
            session.setAttribute("edit", articleID);
        else
            session.setAttribute("edit", -1);
        return "{\"url\":\"writeBlog/editor.html\"}";
    }*/

    @ResponseBody
    @RequestMapping(value = "/editArticle",produces="text/html;charset=UTF-8")
    public String editArticle(@RequestParam("articleID")Integer articleID,HttpSession session){
        System.out.println("editArticle: " + articleID);
        return up.edit(articleID);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String delete(@RequestParam("articleID")Integer articleID){
        articleService.delete(articleID);
        return "{}";
    }




}
