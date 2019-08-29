/** TODO
 * @package  com.bfxy.springboot.producer
 * @file    AcountSender.java
 * @author  lipf
 * @Date    2019年8月28日  下午2:10:20
 * @version   V 1.0
 */
package com.bfxy.springboot.producer;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bfxy.springboot.entity.AccountMessage;
import com.bfxy.springboot.entity.Order;

/**TODO
 * @package  com.bfxy.springboot.producer
 * @file     AcountSender.java
 * @author   lipf
 * @date     2019年8月28日 下午2:10:20
 * @version  V 1.0
 */
@Component
public class AcountSender {
	    //自动注入RabbitTemplate模板类
		@Autowired
		private RabbitTemplate rabbitTemplate;  
		//回调函数: confirm确认,实现一个监听器用于监听Broker端给我们返回的确认请求：
		final ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
			@Override
			public void confirm(CorrelationData correlationData, boolean ack, String cause) {
				System.err.println("correlationData: " + correlationData);
				System.err.println("ack: " + ack);
				if(!ack){
					System.err.println("异常处理....");
				}
			}
		};
		
		//回调函数: return返回：保证消息对Broker是可达的，
		//如果出现路由键不可达的情况，则使用监听器对不可达的消息进行后续的处理，保证消息的路由成功
		final ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
			@Override
			public void returnedMessage(org.springframework.amqp.core.Message message, int replyCode, String replyText,
					String exchange, String routingKey) {
				System.err.println("return exchange: " + exchange + ", routingKey: " 
					+ routingKey + ", replyCode: " + replyCode + ", replyText: " + replyText);
			}
		};
		
		
		
		//发送消息方法调用: 构建自定义订单对象消息,当支付宝扣款成功且数据库存储了消息信息后发送消息
		public void sendChangeAccount(AccountMessage account) throws Exception {
			rabbitTemplate.setConfirmCallback(confirmCallback);
			rabbitTemplate.setReturnCallback(returnCallback);
			//id + 时间戳 全局唯一 
			CorrelationData correlationData = new CorrelationData(account.getMessageId()+""+System.currentTimeMillis());
			rabbitTemplate.convertAndSend("exchange-update-account", "zhifubao.account", account, correlationData);
//			rabbitTemplate.convertAndSend("exchange-2", "springboot.def", account, correlationData);
		}	
}
