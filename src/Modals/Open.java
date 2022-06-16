package Modals;

import java.util.List;

public class Open {
    private List<Maze> mazes;

    /**
     * The constructor method for initiating an Open model.
     * @author Zac Adams
     */
    public Open() {

    }

    /**
     * Update the list of mazes in the open modal instance.
     * @param mazes A list of maze objects to replace the old one
     * @author Zac Adams
     */
    public void updateMazes(List<Maze> mazes) {
        this.mazes = mazes;
    }

    /**
     * Get all mazes stored in the instance of the Open modal.
     * @return A list of last fetched mazes stored in the Open modal instance
     * @author Zac Adams
     */
    public List<Maze> getMazes() {
        return this.mazes;
    }
}
