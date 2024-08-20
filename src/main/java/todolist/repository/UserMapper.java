package todolist.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import todolist.model.User;

@Mapper
public interface UserMapper {
	
	/** ログインユーザー情報取得 */
	public User findLoginUser(String user);
	
	/** ユーザー一覧取得 */
	public List<User> getUsers();
}