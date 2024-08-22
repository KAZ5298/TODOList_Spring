package todolist.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import todolist.model.Item;
import todolist.repository.ItemMapper;
import todolist.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper mapper;

	@Override
	public List<Item> getAllItems() {
		return mapper.getAllItems();
	}
	
	@Override
	public void entryItem(Item item) {
		mapper.entryItem(item);
	}
	
}