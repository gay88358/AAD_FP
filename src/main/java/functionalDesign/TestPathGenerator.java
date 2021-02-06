package functionalDesign;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestPathGenerator {

    private List<Operation> injectOperations;

    TestPathGenerator(List<Operation> injectOperations) {
        this.injectOperations = injectOperations;
    }

    InjectedTestPath generate(TestPath testPath, Level level) {
        List<Action> result = new ArrayList<>();
        Level currentLevel = level;
        for (Event event: testPath.getEvents()) {
            Map.Entry<List<Action>, Level> actionLevelPair = inject(event, currentLevel);
            result.addAll(actionLevelPair.getKey());
            currentLevel = actionLevelPair.getValue();
        }
        return new InjectedTestPath(result);
    }

    private Map.Entry<List<Action>, Level> inject(Event event, Level level) {
        Action eventAction = Action.of(event, level);
        List<Action> operationActions = createOperationActions(event, level);

        List<Action> result = merge(eventAction, operationActions);
        Level lastActionLevel = getLastLevel(result);
        return new AbstractMap.SimpleEntry<>(result, lastActionLevel);
    }

    private List<Action> createOperationActions(Event event, Level level) {
        Level currentLevel = level;
        List<Action> result = new ArrayList<>();
        for (Operation operation : findInjectableOperations(event, level)) {
            currentLevel = currentLevel.record(InjectMetadata.of(event, operation));
            result.add(Action.of(operation, currentLevel));
        }
        return result;
    }

    private List<Operation> findInjectableOperations(Event event, Level level) {
        return injectOperations
                .stream()
                .filter(operation -> level.canInject(InjectMetadata.of(event, operation)))
                .collect(Collectors.toList());
    }

    private Level getLastLevel(List<Action> result) {
        return result.get(result.size() - 1).getLevel();
    }

    private List<Action> merge(Action eventAction, List<Action> operationActions) {
        List<Action> result = new ArrayList<>();
        result.add(eventAction);
        result.addAll(operationActions);
        return result;
    }

}
