package Tests;

import Modals.Maze;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class DataTests {
    Maze maze;
    @BeforeEach
    public void Start() {
        maze = new Maze();
    }
    /**
     * Create random maze object
     * Expect Failure
     */
    @Test
    public void CreateBadMaze() {
        assertThrows(Exception.class, () -> {

        });
    }
    /**
     * Create random maze object
     * Expect Success
     */
    @Test
    public void CreateRandomMaze() {
        assertEquals(1,2);
    }

    /**
     * Save maze data stream to database for regeneration capabilities
     * Test data is valid after saving and generates safe Graphic
     * Expect success
     */
    @Test
    public void SaveMazeToDb() {
        assertEquals(1,2);
    }

    /**
     * Check when maze solution is generated a valid Graphic is formed
     * Expect Success
     */
    @Test
    public void MazeHasSolution() {
        assertEquals(1,2);
    }

    /**
     * Create maze with invalid parameters
     * Expect failure
     */
    @Test
    public void CreateMazeWithInvalidParameters() {
        assertThrows(Exception.class, () -> {

        });
    }

    /**
     * Create a maze with invalid data stream
     * Mimic bad database data
     * Expect failure
     */
    @Test
    public void CreateMazeWithInvalidDataStream() {
        assertThrows(Exception.class, () -> {

        });
    }
}
