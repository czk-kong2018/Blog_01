package com.zhbit.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.zhbit.dto.NotifyInfo;
import com.zhbit.entity.UserMessage;
import com.zhbit.service.interfaces.NotifyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import net.sf.json.JSONArray;

import java.util.List;

@Controller
@RequestMapping("/notify")
public class NotifyController {
	
	@Resource
	NotifyService notify;
	
	@ResponseBody
	@RequestMapping(value = "/manager",produces = "text/html;charset=UTF-8")
	public String manager(@RequestParam("pageNum")Integer pageNum,HttpSession session){
		UserMessage user =(UserMessage) session.getAttribute("user");
		List<NotifyInfo> notifyInfos = notify.notifyInfo(pageNum, 10, user.getUser_name());
		return JSONArray.fromObject(notifyInfos).toString();
	}

	@ResponseBody
	@RequestMapping("/delete")
	public String delete(@RequestParam("notifyID")Integer notifyID){
		notify.delete(notifyID);
		return "{}";
	}

	@ResponseBody
	@RequestMapping("/hasread")
	public String hasread(@RequestParam("notifyID")Integer notifyID){
		notify.hasread(notifyID);
		System.out.println(notifyID);
		return "{}";
	}
	
	
}
