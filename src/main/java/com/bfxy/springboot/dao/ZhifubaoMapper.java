/** TODO
 * @package  com.bfxy.springboot.dao
 * @file    ZhifubaoMapper.java
 * @author  lipf
 * @Date    2019年8月28日  下午2:49:20
 * @version   V 1.0
 */
package com.bfxy.springboot.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.bfxy.springboot.entity.AccountMessage;

/**TODO
 * @package  com.bfxy.springboot.dao
 * @file     ZhifubaoMapper.java
 * @author   lipf
 * @date     2019年8月28日 下午2:49:20
 * @version  V 1.0
 */
@Mapper
public interface ZhifubaoMapper {
	@Update("update t_account set account=account-#{account.account},update_time=now() where user_id=#{account.userId}")
	int updateAccount(@Param("account") AccountMessage account);
	
	@Update("update t_message set status=1 where message_id=#{messageId}")
	int callbackConfirm(@Param("messageId") String  messageId);
	
	@Insert("insert into t_message(user_id,message_id,account,status) values(#{account.userId},#{account.messageId},#{account.account},#{account.status})")
	int insertMessage(@Param("account") AccountMessage account);
}
