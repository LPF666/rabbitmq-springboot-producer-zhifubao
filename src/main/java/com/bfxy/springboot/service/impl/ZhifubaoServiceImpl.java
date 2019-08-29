/** TODO
 * @package  com.bfxy.springboot.service.impl
 * @file    ZhifubaoServiceImpl.java
 * @author  lipf
 * @Date    2019年8月28日  下午3:11:40
 * @version   V 1.0
 */
package com.bfxy.springboot.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bfxy.springboot.dao.ZhifubaoMapper;
import com.bfxy.springboot.entity.AccountMessage;
import com.bfxy.springboot.producer.AcountSender;
import com.bfxy.springboot.service.ZhifubaoService;

/**TODO
 * @package  com.bfxy.springboot.service.impl
 * @file     ZhifubaoServiceImpl.java
 * @author   lipf
 * @date     2019年8月28日 下午3:11:40
 * @version  V 1.0
 */
@Service
public class ZhifubaoServiceImpl implements ZhifubaoService{
	@Autowired
    private ZhifubaoMapper zhifubaoMapper;
	
	@Autowired
    private AcountSender acountSender;
	/** <p>Title: updateAccont</p>
	 * <p>Description: </p>
	 * @param account
	 * @throws Exception 
	 * @see com.bfxy.springboot.service.ZhifubaoService#updateAccont(com.bfxy.springboot.entity.AccountMessage)  
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateAccont(AccountMessage account) throws Exception {
		try {
			//更新账户
			int updateAccount = zhifubaoMapper.updateAccount(account);
			if (updateAccount==1) {
				account.setMessageId(UUID.randomUUID()+""+System.currentTimeMillis());
				int message = zhifubaoMapper.insertMessage(account);
				if (message==1) {
					//如果操作成功则发送消息到队列
					acountSender.sendChangeAccount(account);
				}
			}
		} catch (Exception e) {
			throw new Exception("error-->>"+e.toString());
		}
	}
	/** <p>Title: callbackConfirmMessage</p>
	 * <p>Description: </p>
	 * @param mid
	 * @see com.bfxy.springboot.service.ZhifubaoService#callbackConfirmMessage(java.lang.String)  
	 */
	@Override
	public int callbackConfirmMessage(String mid) {
		int callbackConfirm = zhifubaoMapper.callbackConfirm(mid);
		System.out.println(callbackConfirm);
		return callbackConfirm;
	}

}
