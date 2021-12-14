package day11;

public class Octopus {

    private int energyLevel;
    private int gridRow;
    private int gridCol;
    public static final int MAX_ENERGY_LEVEL = 9;

    public Octopus(int energyLevel, int gridRow, int gridCol) {
        this.energyLevel = energyLevel;
        this.gridCol = gridCol;
        this.gridRow = gridRow;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public int getGridRow() {
        return gridRow;
    }

    public int getGridCol() {
        return gridCol;
    }

    public void incrementEnergyLevel() {
        energyLevel += 1;
    }

    public boolean hasEnergyToFlash() {
        return energyLevel > MAX_ENERGY_LEVEL;
    }

    public void flash() {
        energyLevel = 0;
    }

    /**
     * Completes a step for the octopus, incrementing its energy
     * level and flashing if it becomes greater than its maximum value.
     * 
     * @return True if a flash occurred, false otherwise
     */
    public boolean completeStep() {
        incrementEnergyLevel();
        if (energyLevel > MAX_ENERGY_LEVEL) {
            energyLevel = 0;
            return true;
        }
        return false;
    }



    public boolean equals(Object o) {
        if (!(o instanceof Octopus)) {
            return false;
        }
        Octopus other = (Octopus)o;
        return gridRow == other.getGridRow() && gridCol == other.getGridCol();
    }

    public int hashCode() {
        return String.format("[%d,%d]", gridRow, gridCol).hashCode();
    }


}
