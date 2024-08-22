package todolist.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import todolist.model.Item;

@Mapper
public interface ItemMapper {
	
	/** TODOアイテム一覧取得 */
	public List<Item> getAllItems();
	
	/** TODOアイテム登録 */
	public int entryItem(Item item);
}
