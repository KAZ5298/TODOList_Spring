package todolist.service;

import java.util.Date;
import java.util.List;

import todolist.model.Item;

public interface ItemService {
	public List<Item> getAllItems();
	
	public void entryItem(Item item);
	
	public Item getItemOne(Integer id);
	
	public void editItem(String itemName,
			Integer userId,
			Date expireDate,
			Integer isFinished);
}
