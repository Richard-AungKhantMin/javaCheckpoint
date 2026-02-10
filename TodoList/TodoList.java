package TodoList;

public class TodoList {
    private Task[] tasks;
    private int capacity;
    private int count;
    public TodoList(int capacity){
        this.capacity = capacity;
        this.tasks = new Task[capacity];
        this.count = 0;
    }

    public void addTask(String description){
        if (this.count != this.capacity){
            tasks[this.count] = new Task(description);
            this.count++;
        }
    }

    public void setStatus(int index, TaskStatus status){
        if (index >= 0 && index < this.capacity){
            tasks[index].setStatus(status);
        }
    }

    public void setDescription(int index, String newDescription){
        if (index >= 0 && index < this.capacity){
            tasks[index].setDescription(newDescription);
        }
    }

    public void displayTasks(){
        System.out.println("Tasks:");
        for (int i=0; i<this.count; i++){
            System.out.printf("%-34s| %s\n", this.tasks[i].getDescription(), this.tasks[i].getStatus());
        }
    }
}