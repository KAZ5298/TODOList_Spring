package todolist.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TodoListForm {
	
	private Integer id;
	private Integer userId;
	private String itemName;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date registrationDate;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date expireDate;
	
	private Boolean finishedDate;
	
	private int isDeleted;

}