package todolist.model;

import java.util.Date;

import lombok.Data;

@Data
public class User {
	private Integer id;
	private String user;
	private String pass;
	private String familyName;
	private String firstName;
	private int isAdmin;
	private int isDeleted;
	private Date createDateTime;
	private Date updateDateTime;
}