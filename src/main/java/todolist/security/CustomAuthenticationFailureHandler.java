package todolist.security;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        // エラーメッセージをセッションに設定
        String errorMessage = "ユーザ名かパスワードが正しくありません";
        if (exception.getMessage().contains("削除されたユーザー")) {
            errorMessage = "削除されたユーザーです";
        }
        
//        request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", exception);
        request.getSession().setAttribute("error_message", errorMessage);
        
        System.out.println("Error Message Set in Session: " + errorMessage);
        
        // ログイン画面にリダイレクト
        response.sendRedirect("/login?error");
    }
}
