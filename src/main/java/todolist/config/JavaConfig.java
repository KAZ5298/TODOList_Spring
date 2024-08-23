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

        // ItemのuserIdをTodoListFormのuserIdにマッピング
        modelMapper.addMappings(new PropertyMap<Item, TodoListForm>() {
            @Override
            protected void configure() {
                // ItemのuserIdを直接マッピング
                map().setUserId(source.getUserId());
                // Userオブジェクトのidを直接マッピング
                // この設定は競合を避けるためにコメントアウト
                // map().setUserId(source.getUser() != null ? source.getUser().getId() : null);
            }
        });

        return modelMapper;
    }
}
