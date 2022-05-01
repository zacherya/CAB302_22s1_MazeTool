package DataAccess.Providers;

import Modals.*;

import java.util.List;

public class MazeDataProvider<MazeDto> extends BaseDataProvider<MazeDto> {
    /**
     * Get a particular maze from the database
     * @param id The ID or key that identifies a particular maze
     * @return The maze data from the database as a Maze object
     */
    public Maze GetMaze(String id) {
        return null;
    }

    /**
     * Get all mazes from the database as an interable list object
     * @return A list of Mazes from the database
     */
    public List<Maze> GetMazes() {
        return null;
    }

}
