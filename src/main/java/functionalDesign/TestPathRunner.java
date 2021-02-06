package functionalDesign;

import java.util.Optional;

public class TestPathRunner {
    private TestPathGenerator testPathGenerator;
    private Level level;
    private DeviceService deviceService;

    TestPathRunner(
            TestPathGenerator testPathGenerator,
            Level level,
            DeviceService deviceService) {
        this.testPathGenerator = testPathGenerator;
        this.level = level;

        this.deviceService = deviceService;
    }

    void run(TestPath testPath) {
        run(testPath, level);
    }

    private void run(TestPath testPath, Level level) {
        InjectedTestPath injectedTestPath = testPathGenerator.generate(testPath, level);
        run(injectedTestPath)
                .ifPresent(action -> run(testPath, action.getLevel()));
    }

    private Optional<Action> run(InjectedTestPath injectedTestPath) {
        for (Action action: injectedTestPath.getActions()) {
            Result result = this.deviceService.execute(action);
            if (result.isAnomaly()) {
                return Optional.of(action);
            }
        }
        return Optional.empty();
    }
}
