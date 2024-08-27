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
    
    /** TODOアイテム１件取得 */
    public Item getItemOne(Integer id);
    
    /** TODOアイテム修正 */
    public void editItem(Item item);
    
    /** TODOアイテム削除（論理削除） */
    public void deleteItem(Item item);
    
    /** TODOアイテム完了 */
    public void completeItem(Item item);
    
    /** TODOアイテムを未完了に戻す */
    public void uncompleteItem(Item item);
    
    /** 検索したTODOアイテム一覧取得 */
    public List<Item> searchItems(String searchItem);
    
}
