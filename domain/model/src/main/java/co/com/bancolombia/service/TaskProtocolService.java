package co.com.bancolombia.service;

import java.util.List;

public interface TaskProtocolService {
    void createTask(String name, String priority);
    List<String> listTasks();
}
