package functionalDesign;

import java.util.ArrayList;
import java.util.List;

public class EventLevel implements Level {
    private List<InjectMetadata> eventInjectMetadataList = new ArrayList<>();

    EventLevel() {

    }

    private EventLevel(List<InjectMetadata> eventInjectMetadataList) {
        this.eventInjectMetadataList = eventInjectMetadataList;
    }

    @Override
    public Level record(InjectMetadata injectMetadata) {
        if (!canInject(injectMetadata))
            throw new RuntimeException("Given InjectMetadata is in the level, make sure use canInject to check before record InjectMetadata to level");
        List<InjectMetadata> newEventInjectMetadataList = new ArrayList<>(eventInjectMetadataList);
        newEventInjectMetadataList.add(new InjectMetadata(injectMetadata.event, injectMetadata.operation));
        return new EventLevel(newEventInjectMetadataList);
    }

    @Override
    public boolean canInject(InjectMetadata injectMetadata) {
        return !this.eventInjectMetadataList.contains(new InjectMetadata(injectMetadata.event, injectMetadata.operation));
    }

}
