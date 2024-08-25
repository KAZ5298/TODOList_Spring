package todolist.service;

import java.util.List;

import todolist.model.Item;

public interface ItemService {

	public List<Item> getAllItems();
	
	public void entryItem(Item item);
	
	public Item getItemOne(Integer id);
	
	public void editItem(Item item);
	
	public void deleteItem(Item item);
	
	public void completeItem(Item item);
	
	public List<Item> searchItems(String searchItem);
	
}
