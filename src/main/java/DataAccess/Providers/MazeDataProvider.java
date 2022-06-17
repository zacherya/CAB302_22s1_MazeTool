package DataAccess.Providers;

import DataAccess.Database;
import DataAccess.DtoModels.MazeDto;
import Modals.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Maze data provider extending the base data provider with a polymorphic type case of MazeDto.
 * @param <MazeDto> The type cast of data to retrieve and insert
 * @author Zac Adams
 */
public class MazeDataProvider<MazeDto> extends BaseDataProvider<MazeDto> {
    /**
     * Get a particular maze from the database.
     * @param id The ID or key that identifies a particular maze
     * @return The maze data from the database as a Maze object
     * @author Zac Adams
     */
    public Maze GetMaze(String id) throws SQLException {

        return null;
    }

    public void InsertMaze(DataAccess.DtoModels.MazeDto maze) throws SQLException {
        String mazeStream = Base64.getEncoder().encodeToString(maze.getMazeAsBytes(false));
        String solutionStream = Base64.getEncoder().encodeToString(maze.getMazeAsBytes(true));
       Database.Execute(String.format("INSERT INTO maze (name,author,mazeBinaryStream,solutionBinaryStream) VALUES ('%s','%s','%s','%s');",maze.GetMazeName(),maze.GetMazeAuthor(),mazeStream,solutionStream));
    }

    /**
     * Get all mazes from the database as an iterable list object.
     * @return A list of Mazes from the database
     * @author Zac Adams
     */
    public ArrayList<DataAccess.DtoModels.MazeDto> GetMazes() throws SQLException {
        ResultSet rs = GetEntities(DataAccess.DtoModels.MazeDto.class.getSimpleName());
        ArrayList<DataAccess.DtoModels.MazeDto> mazes = new ArrayList<>();
        while(rs.next()) {
            int mazeId = rs.getInt("id");
            String name = rs.getString("name");
            String author = rs.getString("author");
            byte[] maze = Base64.getDecoder().decode(rs.getString("mazeBinaryStream"));
            byte[] solution = Base64.getDecoder().decode(rs.getString("solutionBinaryStream"));
            DataAccess.DtoModels.MazeDto mazeData = new DataAccess.DtoModels.MazeDto(mazeId,name,author,maze,solution);
            mazes.add(mazeData);
        }
        return mazes;
    }

}
