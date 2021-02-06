package functionalDesign;

public class Action {
    private final Event event;
    private final Level level;

    public static Action of(Event event, Level level) {
        return new Action(event, level);
    }

    private Action(Event event, Level level) {
        this.event = event;
        this.level = level;
    }

    public Event getEvent() {
        return this.event;
    }

    public Level getLevel() {
        return level;
    }
}
