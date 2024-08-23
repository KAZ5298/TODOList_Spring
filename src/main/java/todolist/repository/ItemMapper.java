package todolist.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import todolist.model.Item;

@Mapper
public interface ItemMapper {
	
	/** TODOアイテム一覧取得 */
	public List<Item> getAllItems();
	
	/** TODOアイテム登録 */
	public int entryItem(Item item);
	
	/** TODOアイテム１件取得 */
	public Item getItemOne(Integer id);
	
	/** TODOアイテム修正 */
	public void editItem(@Param("itemName") String itemName,
			@Param("userId") Integer userId,
			@Param("expireDate") Date expireDate,
			@Param("isFinished") Integer isFinished);
	
}
