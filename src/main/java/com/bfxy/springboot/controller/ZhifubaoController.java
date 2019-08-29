/** TODO
 * @package  com.bfxy.springboot.controller
 * @file    ZhifubaoController.java
 * @author  lipf
 * @Date    2019年8月28日  下午3:04:54
 * @version   V 1.0
 */
package com.bfxy.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bfxy.springboot.entity.AccountMessage;
import com.bfxy.springboot.result.CommonResult;
import com.bfxy.springboot.service.ZhifubaoService;

import io.swagger.annotations.Api;

/**TODO
 * @package  com.bfxy.springboot.controller
 * @file     ZhifubaoController.java
 * @author   lipf
 * @date     2019年8月28日 下午3:04:54
 * @version  V 1.0
 */
@Api(value = "ZhifubaoController", description = "支付宝扣款服务")
@Controller
@RequestMapping("/zhifubao")
public class ZhifubaoController {
	
	@Autowired
	private ZhifubaoService  zhifubaoService;
	
	@PostMapping("/updateAccount")
	@ResponseBody
	public  CommonResult<String>  updateAccount(@RequestBody AccountMessage account){
		if (account!=null) {
			account.setAccount(2000.0);
			account.setName("lipf");
			account.setUserId(1001);
			account.setStatus(0);
		}
		try {
			zhifubaoService.updateAccont(account);
		} catch (Exception e) {
			return CommonResult.failed(e.toString());
		}
		return CommonResult.success("ok");
	}
	
	
	@PostMapping("/callbackConfirmMessage")
	@ResponseBody
	public   CommonResult<Integer> callbackConfirmMessage(String mid){
		int callbackConfirmMessage = zhifubaoService.callbackConfirmMessage(mid);
		return CommonResult.success(callbackConfirmMessage);
	}
}
