package functionalDesign;

import java.util.Objects;

public class Operation implements Event {
    private String name;

    public static Operation rotate() {
        return new Operation("rotate");
    }

    public static Operation exit() {
        return new Operation("exit");
    }

    public Operation(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(name, operation.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public EventType getName() {
        return new EventType(this.name);
    }
}
