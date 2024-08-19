package todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import todolist.model.Item;
import todolist.service.ItemService;

@Controller
@Slf4j
public class TodoListController {
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping("/todo")
	public String getTodoList(Model model) {
		
		List<Item> itemList = itemService.getAllItems();
		model.addAttribute(itemList);
		
		log.info(itemList.toString());
		
		return "todo/index";
	}
	
	@GetMapping("/todo/entry")
	public String getTodoEntry(Model model) {
		return "todo/entry";
	}
}