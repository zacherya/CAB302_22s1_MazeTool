package DataAccess.Providers;

import Modals.*;

import java.util.List;

/**
 * Maze data provider extending the base data provider with a polymorphic type case of MazeDto
 * @param <MazeDto> The type cast of data to retrieve and insert
 * @authors Zac Adams
 * @// TODO: 1/05/2022 Look into making this class static 
 */
public class MazeDataProvider<MazeDto> extends BaseDataProvider<MazeDto> {
    /**
     * Get a particular maze from the database
     * @param id The ID or key that identifies a particular maze
     * @return The maze data from the database as a Maze object
     * @authors Zac Adams
     */
    public Maze GetMaze(String id) {
        return null;
    }

    /**
     * Get all mazes from the database as an interable list object
     * @return A list of Mazes from the database
     * @authors Zac Adams
     */
    public List<Maze> GetMazes() {
        return null;
    }

}
