package todolist.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginUserDetails implements UserDetails {
	
	private todolist.model.User user;
	
	public LoginUserDetails(todolist.model.User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO 自動生成されたメソッド・スタブ
		List<GrantedAuthority> authorities = new ArrayList<>();
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO 自動生成されたメソッド・スタブ
		return user.getPass();
	}

	@Override
	public String getUsername() {
		// TODO 自動生成されたメソッド・スタブ
		return user.getUser();
	}
	
	public String getFullname() {
		return user.getFamilyName() + user.getFirstName();
	}
	
	@Override
    public boolean isEnabled() {
        return user.getIsDeleted() != 1; // ユーザーが削除されていない場合は true を返す
    }

}
