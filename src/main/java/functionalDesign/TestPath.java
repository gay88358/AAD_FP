package functionalDesign;

import java.util.List;
import java.util.Objects;

public class TestPath {
    private List<Event> events;

    public static TestPath of(List<Event> events) {
        return new TestPath(events);
    }

    private TestPath(List<Event> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestPath testPath = (TestPath) o;
        return Objects.equals(events, testPath.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(events);
    }

    public List<Event> getEvents() {
        return this.events;
    }
}
