package todolist.form;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import todolist.model.User;

@Data
public class TodoListForm {
    
    private Integer id;
    
    @NotNull(message = "担当者は必ず選択してください")
    private Integer userId;
    
    @Size(max = 100, message = "項目名は100文字以内でなければなりません。")
    @NotBlank(message = "項目名は必須です。")
    private String itemName;
    
    private Date registrationDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "期限は必ず入力してください")
    private Date expireDate;
    
    private Date finishedDate;
    
    private Boolean isFinished;
    
    private Integer isDeleted;
    
    private User user;

}
