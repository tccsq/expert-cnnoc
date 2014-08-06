package cn.com.cnnoc.expert.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;

import cn.com.cnnoc.expert.dao.ArticleDao;
import cn.com.cnnoc.expert.model.Article;
import cn.com.cnnoc.expert.model.Channel;
import cn.com.cnnoc.expert.util.CommonUtil;
import cn.com.cnnoc.expert.util.DBUtil;
import cn.com.cnnoc.expert.vo.PagerVO;

public class ArticleDaoImpl extends BaseDaoImpl implements ArticleDao {

	public void deleteArticle(int id) {
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;

		String sql = "delete from t_article where id = ?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, id);

			pstmt.executeUpdate();

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			DBUtil.rollback(conn);
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}

	public Article findArticleById(int id) {
		Article article = new Article();
		
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from t_article where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				article.setContent(rs.getString("content"));
				article.setCreateTime(rs.getTimestamp("createTime"));
				article.setDeployTime(rs.getTimestamp("deployTime"));
				article.setTitle(rs.getString("title"));
				article.setUpdateTime(rs.getTimestamp("updateTime"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}

		return article;
	}

	public PagerVO findArticleList(String title,String createTime,int pagesize,int offset) {
		PagerVO pagerVO = new PagerVO();
		
		
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from t_article where 1= 1";
		String sqlForCount = "select count(1) from t_article where 1 = 1";
		
		if(!CommonUtil.isNullOrBlank(title)){
			sql = sql + " and title like '%" + title +"%'";
			sqlForCount = sqlForCount + " and title like '%" + title +"%'";
		}
		if(!CommonUtil.isNullOrBlank(createTime)){
			sql = sql + " and  date_format(createTime, '%Y-%m-%d') = '" + createTime + "'";
			sqlForCount = sqlForCount + " and  date_format(createTime, '%Y-%m-%d') = '" + createTime + "'";
		}
		sql = sql + " limit ?,? ";
		
		List<Article> articles = new ArrayList<Article>();
		int total = 0;
		
		try {
			pstmt = conn.prepareStatement(sqlForCount);
			rs = pstmt.executeQuery();
			
			if(rs.next()){				
				total = rs.getInt(1);
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, offset);
			pstmt.setInt(2, pagesize);
			
			rs = pstmt.executeQuery();
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				Article article = new Article();
				article.setId(rs.getInt("id"));
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				article.setCreateTime(rs.getTimestamp("createTime"));
				article.setUpdateTime(rs.getTimestamp("updateTime"));
				article.setDeployTime(rs.getTimestamp("deployTime"));
				articles.add(article);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		
		pagerVO.setDatas(articles);
		pagerVO.setTotal(total);
		
		return pagerVO;
	}

	public void addArticle(Article article) {
		SqlSession session = getSessionFactory().openSession();
		try {
			session.insert(Article.class.getName() + ".insert", article);
			
			Set<Channel> channels = article.getChannels();
			
			for (Channel c : channels) {
				Map<String ,Object> params = new HashMap<String, Object>();
				params.put("a", article);
				params.put("c", c);
				session.insert(Article.class.getName() + ".insertArticleChannel", params);
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}

	public void updateArticle(Article article) {
		Connection conn = DBUtil.getConn();
		PreparedStatement pstmt = null;

		String sql = "update t_article set title=?,content=?,updateTime=? where id = ?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getContent());
			pstmt.setTimestamp(3, new Timestamp(new Date().getTime()));
			pstmt.setInt(4, article.getId());

			pstmt.executeUpdate();

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			DBUtil.rollback(conn);
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}

	}

}
