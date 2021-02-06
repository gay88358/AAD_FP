package functionalDesign;


import java.util.Objects;

public class InjectMetadata {
    final Event event;
    final Operation operation;

    static InjectMetadata of(Event event, Operation operation) {
        return new InjectMetadata(event, operation);
    }

    public InjectMetadata(Event event, Operation operation) {

        this.event = event;
        this.operation = operation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InjectMetadata that = (InjectMetadata) o;
        return Objects.equals(event, that.event) &&
                Objects.equals(operation, that.operation);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}