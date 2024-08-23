package todolist.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import todolist.form.TodoListForm;
import todolist.model.Item;
import todolist.model.User;
import todolist.service.ItemService;
import todolist.service.UserService;

@Controller
@Slf4j
public class TodoListController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// 作業一覧画面表示
	@GetMapping("/todo")
	public String getTodoList(Model model) {
		
		List<Item> itemList = itemService.getAllItems();
		model.addAttribute("itemList", itemList);
		
		log.info(itemList.toString());
		
		return "todo/index";
	}
	
	// 作業登録画面表示
	@GetMapping("/todo/entry")
	public String getTodoEntry(Model model, @ModelAttribute TodoListForm todoListForm) {
		
		List<User> userList = userService.getUsers();
		model.addAttribute("userList", userList);
		
		log.info(userList.toString());
		
		return "todo/entry";
	}
	
	// 作業登録機能
	@PostMapping("/todo/entry")
	public String postTodoEntry(Model model, @ModelAttribute TodoListForm todoListForm) {
		
		Item item = modelMapper.map(todoListForm, Item.class);
		
		itemService.entryItem(item);
		
		return "redirect:/todo";
	}
	
	// 作業修正画面表示
	@GetMapping("/todo/edit/{id}")
	public String getTodoEdit(Model model, TodoListForm todoListForm, @PathVariable("id") Integer id) {
		
		List<User> userList = userService.getUsers();
		
		model.addAttribute("userList", userList);
		
		log.info(userList.toString());
		
		Item item = itemService.getItemOne(id);
		
		todoListForm = modelMapper.map(item, TodoListForm.class);
		
		model.addAttribute("todoListForm", todoListForm);
		
		log.info(todoListForm.toString());
		
		return "todo/edit";
	}
	
	// 作業修正機能
	@PostMapping("/todo/edit/{id}")
	public String postTodoEdit(Model model, TodoListForm todoListForm, @PathVariable("id") Integer id) {
		
		Item item = itemService.getItemOne(id);
		
		if (item != null) {
			item.setItemName(todoListForm.getItemName());
			item.setUserId(todoListForm.getUserId());
			item.setExpireDate(todoListForm.getExpireDate());
			item.setIsFinished(todoListForm.getIsFinished());
			
			itemService.editItem(item);
		}
		
		return "redirect:/todo";
	}
	
	// 作業削除画面表示
	@GetMapping("/todo/delete/{id}")
	public String getTodoDelete(Model model, TodoListForm todoListForm, @PathVariable("id") Integer id) {
		
		List<User> userList = userService.getUsers();
		
		model.addAttribute("userList", userList);
		
		log.info(userList.toString());
		
		Item item = itemService.getItemOne(id);
		
		todoListForm = modelMapper.map(item, TodoListForm.class);
		
		model.addAttribute("todoListForm", todoListForm);
		
		log.info(todoListForm.toString());
		
		return "todo/delete";
	}
	
	// 作業削除機能
	@PostMapping("/todo/delete/{id}")
	public String postTodoDelete(Model model, TodoListForm todoListForm, @PathVariable("id") Integer id) {
		
		Item item = itemService.getItemOne(id);
		
		if (item != null) {
			itemService.deleteItem(item);
		}
		
		return "redirect:/todo";
	}
	
	// 作業完了機能
	@PostMapping("/todo/complete")
	public String postTodoComplete(Model model, TodoListForm todoListForm, @RequestParam("itemId") Integer id) {
		
		Item item = itemService.getItemOne(id);
		
		if (item != null) {
			itemService.completeItem(item);
		}
		
		return "redirect:/todo";
	}
}