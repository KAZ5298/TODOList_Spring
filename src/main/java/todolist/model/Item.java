package todolist.model;

import java.util.Date;

import lombok.Data;

@Data
public class Item {
	private Integer id;
	private Integer userId;
	private String itemName;
	private Date registrationDate;
	private Date expireDate;
	private Date finishedDate;
	private Boolean isFinished;
	private Integer isDeleted;
	private User user;
}
