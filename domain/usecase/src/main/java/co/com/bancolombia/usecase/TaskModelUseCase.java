package co.com.bancolombia.usecase;

import co.com.bancolombia.service.TaskProtocolService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TaskModelUseCase {
    private final TaskProtocolService context;

    public Object act(String command) {
        if (command.contains("crear") && command.contains("prioridad")) {
            String name = command.split("‘")[1].split("’")[0];
            String priority = command.contains("alta") ? "Alta" : "Normal";
            context.createTask(name, priority);
            return "✅ Tarea creada con éxito.";
        } else if (command.contains("listar")) {
            return context.listTasks();
        } else {
            return "🤖 No entendí el comando.";
        }
    }
}
