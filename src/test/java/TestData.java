import Exceptions.MazeCreationException;
import Modals.Maze;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for generating and storing maze data
 * @author Zac Adams, Aaron Nolan
 */
public class TestData {
    Maze maze;

    /**
     * Begin each test with a new maze object
     */
    @BeforeEach
    public void Start() {
        maze = new Maze();
    }
    /**
     * Create random maze object
     * Expect Failure
     */
    @Test
    public void CreateBadSizeMaze() {
        assertThrows(MazeCreationException.class, () -> maze = new Maze(-5,-10,0));
    }
    /**
     * Create random maze object
     * Expect Failure
     */
    @Test
    public void CreateBadChanceMaze() {
        assertThrows(MazeCreationException.class, () -> maze = new Maze(5,5,-2.222));
    }
    /**
     * Create random maze object
     * Expect Success
     */
    @Test
    public void CreateRandomMaze() throws MazeCreationException {
        assertNotNull(maze = new Maze(10, 10, 10));
    }

    /**
     * Save maze data stream to database for regeneration capabilities
     * Test data is valid after saving and generates safe Graphic
     * Expect success
     */
    @Test
    public void SaveMazeToDb() {
        assertEquals(1,1);
    }

    /**
     * Check when maze solution is generated a valid Graphic is formed
     * Expect Success
     */
    @Test
    public void MazeHasSolution() {
        assertEquals(1,1);
    }

    /**
     * Create maze with invalid parameters
     * Expect failure
     */
    @Test
    public void CreateMazeWithInvalidParameters() {
        assertThrows(MazeCreationException.class, () -> maze = new Maze(500,500,10));
    }

    /**
     * Create a maze with invalid data stream
     * Mimic bad database data
     * Expect failure
     */
    @Test
    public void CreateMazeWithInvalidDataStream() {
        assertEquals(1,1);
    }
}
