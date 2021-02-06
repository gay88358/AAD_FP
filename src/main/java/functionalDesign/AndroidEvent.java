package functionalDesign;

import java.util.Objects;

public class AndroidEvent implements Event {

    private String name;

    public AndroidEvent(String name) {

        this.name = name;
    }

    public static AndroidEvent click(String name) {
        return new AndroidEvent(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AndroidEvent that = (AndroidEvent) o;
        return this.name.equals(that.name);
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
