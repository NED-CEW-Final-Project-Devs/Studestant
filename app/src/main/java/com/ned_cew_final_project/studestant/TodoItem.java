package com.ned_cew_final_project.studestant;

public class TodoItem
{
    private String title;
    private String details;
    private boolean isDone;

    // constructor
    public  TodoItem(String title, String details)
    {
        this.title = title;
        this.details = details;
        this.isDone = false;
    }

    // empty constructor for firebase. DON'T REMOVE
    public  TodoItem()
    {

    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public boolean getIsDone() {
        return isDone;
    }


}
