package com.zhbit.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.zhbit.dao.NotifyDao;
import com.zhbit.dto.NotifyInfo;
import com.zhbit.service.interfaces.NotifyService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NotifyServiceImpl implements NotifyService {
	
	@Autowired
	private NotifyDao notifyDao;

	public List<NotifyInfo> notifyInfo(int pageNum, int countPerPage, String userName) {

		List<NotifyInfo> notifyInfos=notifyDao.notifyInfo(/*(pageNum-1)*countPerPage, countPerPage, */userName);

		return notifyInfos;
	}


	public void delete(int notifyID) {

		notifyDao.delete(notifyID);
	}

	public void hasread(int notifyID) {

		notifyDao.hasread(notifyID);

	}

	public void deleteAllByArticleId(int article_id) {
		notifyDao.delete(article_id);
	}

}
