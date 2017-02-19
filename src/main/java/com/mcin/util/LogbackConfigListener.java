package com.mcin.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

/**
 *
 * @author mcin
 * 保存日志文件
 */
public class LogbackConfigListener implements ServletContextListener {
	public void contextInitialized(ServletContextEvent event) {

		String path = event.getServletContext().getRealPath("/WEB-INF/");

		File file = new File(path,"logs");
		if (!file.exists()) {
			file.mkdir();
		}
		System.setProperty("webapp.path", path);
		LogInfo.info("加载日志成功");
	}

	public void contextDestroyed(ServletContextEvent arg0) {

	}
}
