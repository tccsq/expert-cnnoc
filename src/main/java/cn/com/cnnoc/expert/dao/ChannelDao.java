package cn.com.cnnoc.expert.dao;

import java.util.List;

import cn.com.cnnoc.expert.model.Channel;

public interface ChannelDao extends BaseDao {
	public void addChannel(Channel channel);

	public void deleteChannel(int channelId);

	public List<Channel> searchChannels();

	public void updateChannel(Channel channel);
}
