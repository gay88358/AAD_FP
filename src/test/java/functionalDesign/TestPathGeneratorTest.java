package functionalDesign;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestPathGeneratorTest {
    private final static AndroidEvent click1 = AndroidEvent.click("e1");
    private final static AndroidEvent click2 = AndroidEvent.click("e2");
    private final static AndroidEvent click3 = AndroidEvent.click("e3");
    private final static Operation rotate = Operation.rotate();
    private final static Operation exit = Operation.exit();
    private static List<Operation> injectOperations;

    @BeforeEach
    public void setup() {
        injectOperations = Arrays.asList(rotate, exit);
    }

    @Test
    public void crash_at_second_rotate() {
        // Arrange
        TestPath testPath = createTestPath();
        TestPathGenerator generator = createTestPathGenerator();
        InjectedTestPath injectedTestPath = createInjectedTestPath(testPath, generator);
        // Act
        Action secondRotateAction = injectedTestPath.getAction(4);
        InjectedTestPath result = generator.generate(testPath, secondRotateAction.getLevel());
        // Assert
        assertInjectedTestPath(result,
                click1,
                click2,
                Operation.exit(),
                click3,
                Operation.rotate(),
                Operation.exit()
        );
    }

    @Test
    public void crash_at_first_exit() {
        // Arrange
        TestPath testPath = createTestPath();
        TestPathGenerator generator = createTestPathGenerator();
        InjectedTestPath injectedTestPath = createInjectedTestPath(testPath, generator);
        // Act
        Action firstExitAction = injectedTestPath.getAction(2);
        InjectedTestPath result = generator.generate(testPath, firstExitAction.getLevel());
        // Assert
        assertInjectedTestPath(result,
                click1,
                click2,
                Operation.rotate(),
                Operation.exit(),
                click3,
                Operation.rotate(),
                Operation.exit()
        );
    }

    private InjectedTestPath createInjectedTestPath(TestPath testPath, TestPathGenerator generator) {
        Level level = createEventLevel();
        return generator.generate(testPath, level);
    }

    @Test
    public void crash_at_first_rotate() {
        // Arrange
        TestPath testPath = createTestPath();
        Level level = createEventLevel();
        TestPathGenerator generator = createTestPathGenerator();
        InjectedTestPath injectedTestPath = generator.generate(testPath, level);
        // Act
        Action firstRotateAction = injectedTestPath.getAction(1);
        InjectedTestPath result = generator.generate(testPath, firstRotateAction.getLevel());
        // Assert
        assertInjectedTestPath(result,
                click1,
                Operation.exit(),
                click2,
                Operation.rotate(),
                Operation.exit(),
                click3,
                Operation.rotate(),
                Operation.exit()
        );
    }


    @Test
    public void generate_rotate_exit_test_path_without_crash() {
        // Arrange
        TestPath testPath = createTestPath();
        TestPathGenerator generator = createTestPathGenerator();
        InjectedTestPath injectedTestPath = createInjectedTestPath(testPath, generator);
        // Assert
        assertInjectedTestPath(injectedTestPath,
                click1,
                Operation.rotate(),
                Operation.exit(),
                click2,
                Operation.rotate(),
                Operation.exit(),
                click3,
                Operation.rotate(),
                Operation.exit()
        );
    }

    private void assertInjectedTestPath(InjectedTestPath injectedTestPath, Event ...events) {
        TestPath actualTestPath = injectedTestPath.getTestPath();
        TestPath expectedTestPath = TestPath.of(Arrays.asList(events));
        assertEquals(expectedTestPath, actualTestPath);
    }

    private TestPathGenerator createTestPathGenerator() {
        return new TestPathGenerator(injectOperations);
    }

    private TestPath createTestPath() {
        return TestPath.of(Arrays.asList(click1, click2, click3));
    }

    private Level createEventLevel() {
        return new EventLevel();
    }
}