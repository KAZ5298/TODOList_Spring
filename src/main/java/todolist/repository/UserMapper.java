package todolist.repository;

import org.apache.ibatis.annotations.Mapper;

import todolist.model.User;

@Mapper
public interface UserMapper {
	
	/** ログインユーザー情報取得 */
	public User findLoginUser(String user);
}