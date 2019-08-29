/** TODO
 * @package  com.bfxy.springboot.service
 * @file    ZhifubaoService.java
 * @author  lipf
 * @Date    2019年8月28日  下午3:10:49
 * @version   V 1.0
 */
package com.bfxy.springboot.service;

import org.springframework.stereotype.Service;

import com.bfxy.springboot.entity.AccountMessage;

/**TODO
 * @package  com.bfxy.springboot.service
 * @file     ZhifubaoService.java
 * @author   lipf
 * @date     2019年8月28日 下午3:10:49
 * @version  V 1.0
 */

public interface ZhifubaoService {

	/**@author lipf
	 * TODO
	 * @method updateAccont
	 * @param account
	 * @return void
	 * @throws Exception 
	 * @date 2019年8月28日 下午3:16:20
	 */
	void updateAccont(AccountMessage account) throws Exception;

	/**@author lipf
	 * TODO
	 * @method callbackConfirmMessage
	 * @param mid
	 * @return void
	 * @date 2019年8月28日 下午5:22:16
	 */
	int callbackConfirmMessage(String mid);
	
}
