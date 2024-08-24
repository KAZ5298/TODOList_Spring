package todolist.aspect;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerAdvice {
	
	/** データベース関連例外処理 */
	@ExceptionHandler(DataAccessException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {
		
		model.addAttribute("error", "データベースエラーが発生しました。");
		model.addAttribute("message", e.getMessage());
		
		return "error";
	}
	
	/** その他例外処理 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionHandler(Exception e, Model model) {
		
		model.addAttribute("error", "予期しないエラーが発生しました。");
		model.addAttribute("message", e.getMessage());
		
		return "error";
	}

}
