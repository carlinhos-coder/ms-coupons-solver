package co.com.bancolombia.api.handler;

import co.com.bancolombia.usecase.TaskModelUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mcp")
public class TaskController {

    private final TaskModelUseCase model;

    public TaskController(TaskModelUseCase model) {
        this.model = model;
    }

    @PostMapping("/comando")
    public Object interpretarComando(@RequestBody String comando) {
        return model.act(comando);
    }
}
