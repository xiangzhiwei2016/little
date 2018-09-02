
/**
 * @author xiangzhiwei
 * 若一个方法一次执行不完下次轮转时则等待改方法执行完后才执行下一次操作
 */
package com.fengdu.schedule;

import java.io.File;


import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fengdu.constant.MyshopConstants;
import com.fengdu.utils.SpringContextUtils;
import com.fengdu.utils.property.Configuration;

@DisallowConcurrentExecution
public class ClearImagesJob implements Job{
	private static final Logger logger = LoggerFactory.getLogger(ClearImagesJob.class);
	
	private Configuration configuration;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
//		Object obj = context.getMergedJobDataMap().get("xzw");
		configuration = (Configuration) SpringContextUtils.getBean("configuration");

		String linuxPath = configuration.getProperty(MyshopConstants.LINUX_OLD_PATH,
				MyshopConstants.DEFAULT_LINUX_OLD_PATH);
		File fileDir = new File(linuxPath); 
		logger.info("定时任务，每晚12点，清理图片路径：{}",linuxPath);
		deleteDir(fileDir);
		logger.info("定时任务，每晚12点，清理图片结束");
	}
	
    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return
     */
    private boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            // 递归删除
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return Boolean.FALSE;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
} 