package todolist.controller;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import todolist.form.TododeleteForm;
import todolist.form.TodoeditForm;
import todolist.form.TodoentryForm;
import todolist.model.Item;
import todolist.model.User;
import todolist.service.ItemService;
import todolist.service.UserService;

@Controller
@Slf4j
public class TodoListController {
    
    @Autowired
    private ItemService itemService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ModelMapper modelMapper;
    
    // 作業一覧画面表示
    @GetMapping("/todo")
    public String getTodoList(@RequestParam(name = "searchItem", required = false) String searchItem,
            Model model, HttpServletRequest request) {
        
        model.addAttribute("request", request);
        
        List<Item> itemList;
        
        if (searchItem == null || searchItem.isEmpty()) {
            // 全作業一覧取得
            itemList = itemService.getAllItems();
        } else {
            // 検索作業一覧取得（項目名と担当者名）
            itemList = itemService.searchItems(searchItem);
        }
        
        model.addAttribute("itemList", itemList);
        
        model.addAttribute("searchItem", searchItem);
        
        return "todo/index";
        
    }
    
    // 作業登録画面表示
    @GetMapping("/todo/entry")
    public String getTodoEntry(@ModelAttribute TodoentryForm todoentryForm, Model model, HttpServletRequest request) {
        
        model.addAttribute("request", request);
        
        List<User> userList = userService.getUsers();
        
        model.addAttribute("userList", userList);
        
        return "todo/entry";
        
    }
    
    // 作業登録機能
    @PostMapping("/todo/entry")
    public String postTodoEntry(@ModelAttribute @Valid TodoentryForm todoentryForm,
            BindingResult bindingResult, Model model, HttpServletRequest request) {
        
        model.addAttribute("request", request);
        
        if (bindingResult.hasErrors()) {
            List<User> userList = userService.getUsers();
            model.addAttribute("userList", userList);
            
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            
            model.addAttribute("errorMessages", errorMessages);
            
            return "todo/entry";
        }
        
        Item item = modelMapper.map(todoentryForm, Item.class);
        
        itemService.entryItem(item);
        
        return "redirect:/todo";
        
    }
    
    // 作業修正画面表示
    @GetMapping("/todo/edit/{id}")
    public String getTodoEdit(Model model, HttpServletRequest request, @PathVariable("id") Integer id) {
        
        model.addAttribute("request", request);
        
        List<User> userList = userService.getUsers();
        
        model.addAttribute("userList", userList);
        
        Item item = itemService.getItemOne(id);
        
        if (item == null) {
            // アイテムが見つからない場合の処理
            return "redirect:/todo";
        }
        
        TodoeditForm todoeditForm = modelMapper.map(item, TodoeditForm.class);
        // finishedDate が null でない場合に isFinished を true に設定
        todoeditForm.setIsFinished(item.getFinishedDate() != null);
        
        model.addAttribute("todoeditForm", todoeditForm);
        
        return "todo/edit";
        
    }
    
    // 作業修正機能
    @PostMapping("/todo/edit/{id}")
    public String postTodoEdit(@ModelAttribute @Valid TodoeditForm todoeditForm,
            BindingResult bindingResult, @PathVariable("id") Integer id, Model model, HttpServletRequest request) {
        
        model.addAttribute("request", request);
        
        if (bindingResult.hasErrors()) {
            List<User> userList = userService.getUsers();
            model.addAttribute("userList", userList);
            
            // エラーメッセージをModelに追加
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            
            model.addAttribute("errorMessages", errorMessages);
            
            return "todo/edit";
        }
        
        // 正常処理
        Item item = itemService.getItemOne(id);
        if (item != null) {
            item.setItemName(todoeditForm.getItemName());
            item.setUserId(todoeditForm.getUserId());
            item.setExpireDate(todoeditForm.getExpireDate());
            item.setIsFinished(todoeditForm.getIsFinished() != null ? todoeditForm.getIsFinished() : false);
            
            itemService.editItem(item);
        }
        
        return "redirect:/todo";
    }
    
    // 作業削除画面表示
    @GetMapping("/todo/delete/{id}")
    public String getTodoDelete(Model model, HttpServletRequest request, @PathVariable("id") Integer id) {
        
        model.addAttribute("request", request);
        
        Item item = itemService.getItemOne(id);
        
        if (item == null) {
            // アイテムが見つからない場合の処理
            return "redirect:/todo";
        }
        
        TododeleteForm tododeleteForm = modelMapper.map(item, TododeleteForm.class);
        // finishedDate が null でない場合に isFinished を true に設定
        tododeleteForm.setIsFinished(item.getFinishedDate() != null);
        
        // Userオブジェクトをセット
        tododeleteForm.setUser(item.getUser());
        
        model.addAttribute("tododeleteForm", tododeleteForm);
        
        return "todo/delete";
        
    }
    
    // 作業削除機能
    @PostMapping("/todo/delete/{id}")
    public String postTodoDelete(Model model, @PathVariable("id") Integer id) {
        
        Item item = itemService.getItemOne(id);
        
        if (item != null) {
            itemService.deleteItem(item);
        }
        
        return "redirect:/todo";
    }
    
    // 作業完了機能
    @PostMapping("/todo/complete")
    public String postTodoComplete(Model model, @RequestParam("itemId") Integer id) {
        
        Item item = itemService.getItemOne(id);
        
        if (item != null) {
            itemService.completeItem(item);
        }
        
        return "redirect:/todo";
    }
    
    // 作業完了機能
    @PostMapping("/todo/uncomplete")
    public String postTodoUncomplete(Model model, @RequestParam("itemId") Integer id) {
        
        Item item = itemService.getItemOne(id);
        
        if (item != null) {
            itemService.uncompleteItem(item);
        }
        
        return "redirect:/todo";
    }
}