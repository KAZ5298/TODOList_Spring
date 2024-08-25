package todolist.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerAdvice {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    /** データベース関連例外処理 */
    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String dataAccessExceptionHandler(DataAccessException e, Model model) {
        
        logger.error("データベースエラーが発生しました: {}", e.getMessage(), e);
        
        model.addAttribute("error", "データベースエラーが発生しました。");
        model.addAttribute("message", e.getMessage());
        
        return "error";
    }
    
    /** その他例外処理 */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exceptionHandler(Exception e, Model model) {
        
        logger.error("予期しないエラーが発生しました: {}", e.getMessage(), e);
        
        model.addAttribute("error", "予期しないエラーが発生しました。");
        model.addAttribute("message", e.getMessage());
        
        return "error";
    }

}
