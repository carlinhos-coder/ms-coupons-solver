package co.com.bancolombia.consumer.service;

import co.com.bancolombia.service.TaskProtocolService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class TaskContext implements TaskProtocolService {
    private final List<String> tasks = new ArrayList<>();

    @Override
    public void createTask(String name, String priority) {
        String task = "Tarea: " + name + " | Prioridad: " + priority;
        tasks.add(task);
    }

    @Override
    public List<String> listTasks() {
        return tasks;
    }
}
