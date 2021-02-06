package functionalDesign;

public interface Level {
    Level record(InjectMetadata injectMetadata);
    boolean canInject(InjectMetadata injectMetadata);
}
