package todolist.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import todolist.form.TodoListForm;
import todolist.model.Item;

@Configuration
public class JavaConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		// ItemからTodoListFormへのマッピング設定
		modelMapper.addMappings(new PropertyMap<Item, TodoListForm>() {
			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setUserId(source.getUserId());
				map().setItemName(source.getItemName());
				map().setRegistrationDate(source.getRegistrationDate());
				map().setExpireDate(source.getExpireDate());
				map().setFinishedDate(source.getFinishedDate());
				map().setIsFinished(source.getIsFinished());
				map().setIsDeleted(source.getIsDeleted());
				map().setUser(source.getUser());
			}
		});

		// TodoListFormからItemへのマッピング設定
		modelMapper.addMappings(new PropertyMap<TodoListForm, Item>() {
			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setUserId(source.getUserId());
				map().setItemName(source.getItemName());
				map().setRegistrationDate(source.getRegistrationDate());
				map().setExpireDate(source.getExpireDate());
				map().setFinishedDate(source.getFinishedDate());
				map().setIsFinished(source.getIsFinished());
				map().setIsDeleted(source.getIsDeleted());
				map().setUser(source.getUser());
			}
		});

		return modelMapper;
	}
}
