package functionalDesign;

public class DeviceService {

    private StateChecker stateChecker;

    DeviceService(StateChecker stateChecker) {

        this.stateChecker = stateChecker;
    }

    public Result execute(Action action) {
        if (action.getEvent().getName().equals("click")) {
            // code to click
        } else if (action.getEvent().getName().equals("rotate")) {
            // code to rotate
        } else if (action.getEvent().getName().equals("exit")) {
            // code to exit
        }
        return this.stateChecker.check();
    }
}
