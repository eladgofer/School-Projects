package com.example.eladgofer.project.utils;

/**
 * Created by eladgofer on 11/07/2017.
 */

public class TodoListItem {
    private String name;
    private String owner;
    private String todoItemId;
    private String todoListId;
    private boolean isDeleted = false;

    public TodoListItem(String name, String owner, String todoItemId, String todoListId, boolean isDeleted) {
        this.name = name;
        this.owner = owner;
        this.todoItemId = todoItemId;
        this.todoListId = todoListId;
        this.isDeleted = isDeleted;
    }

    public TodoListItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTodoItemId() {
        return todoItemId;
    }

    public void setTodoItemId(String todoItemId) {
        this.todoItemId = todoItemId;
    }

    public String getTodoListId() {
        return todoListId;
    }

    public void setTodoListId(String todoListId) {
        this.todoListId = todoListId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "TodoListItem{" +
                "name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", todoItemId='" + todoItemId + '\'' +
                ", todoListId='" + todoListId + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
