package functionalDesign;

import java.util.Objects;

public class EventType {
    private final String name;
    EventType(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventType eventType = (EventType) o;
        return Objects.equals(name, eventType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
