package todolist.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import todolist.form.TodoeditForm;
import todolist.model.Item;

@Configuration
public class JavaConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        
        // ItemからTodoeditFormへのマッピング設定
        modelMapper.addMappings(new PropertyMap<Item, TodoeditForm>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setUserId(source.getUserId());
                map().setItemName(source.getItemName());
                map().setExpireDate(source.getExpireDate());
                map().setIsFinished(source.getIsFinished());
            }
        });
        
        // TodoeditFormからItemへのマッピング設定
        modelMapper.addMappings(new PropertyMap<TodoeditForm, Item>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setUserId(source.getUserId());
                map().setItemName(source.getItemName());
                map().setExpireDate(source.getExpireDate());
                map().setIsFinished(source.getIsFinished());
            }
        });
        
        return modelMapper;
    }
}
