package com.zhbit.service.interfaces;

import java.util.List;

import com.zhbit.dto.NotifyInfo;
import org.apache.ibatis.annotations.Param;

public interface NotifyService {
	
	 List<NotifyInfo> notifyInfo(int pageNum, int countPerPage, String userName);
	 void delete(int notifyID);
	 void hasread(int notifyID);

	void deleteAllByArticleId(int article_id);
	
}
