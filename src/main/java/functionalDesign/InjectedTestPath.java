package functionalDesign;

import java.util.List;
import java.util.stream.Collectors;

public class InjectedTestPath {
    private List<Action> actions;

    public InjectedTestPath(List<Action> actions) {
        this.actions = actions;
    }

    public TestPath getTestPath() {
        List<Event> events = this.actions
                .stream()
                .map(Action::getEvent)
                .collect(Collectors.toList());
        return TestPath.of(events);
    }

    public Action getAction(int index) {
        return this.actions.get(index);
    }

    public List<Action> getActions() {
        return this.actions;
    }
}
