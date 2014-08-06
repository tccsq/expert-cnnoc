package cn.com.cnnoc.expert.dao;


import cn.com.cnnoc.expert.model.Article;
import cn.com.cnnoc.expert.vo.PagerVO;

public interface ArticleDao extends BaseDao{
	public PagerVO findArticleList(String title,String createTime,int pagesize,int offset);
	public void deleteArticle(int id);
	public void updateArticle(Article article);
	public void addArticle(Article article);
	public Article findArticleById(int id);
}
