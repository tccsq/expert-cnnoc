package cn.com.cnnoc.expert.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.com.cnnoc.expert.dao.ChannelDao;
import cn.com.cnnoc.expert.model.Channel;

public class ChannelDaoImpl extends BaseDaoImpl implements ChannelDao {

	public void addChannel(Channel channel) {

		SqlSession session = getSessionFactory().openSession();

		try {
			session.insert(Channel.class.getName() + ".insert", channel);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}

	}

	public void deleteChannel(int channelId) {
		SqlSession session = getSessionFactory().openSession();
		
		try {
			session.delete(Channel.class.getName()+".delete", channelId);
			session.delete(Channel.class.getName()+".delete_channel_article", channelId);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally{
			session.close();
		}
	}

	public List<Channel> searchChannels() {
		SqlSession session = getSessionFactory().openSession();
		List<Channel> list = null;
		
		try {
			list = session.selectList(Channel.class.getName()+".selectAll");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}

	public void updateChannel(Channel channel) {
		SqlSession session = getSessionFactory().openSession();

		try {
			session.update(Channel.class.getName() + ".update", channel);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}

}
