package other;
/**defines an InHouse part.
 * extends(subclass of) parts class
 * */
public class InHouse extends Part {
    private int machineId;
    /**creates an inhouse part. */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        //super keyword calls the superclass method
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**returns the machineid for the part
     * code tab, generate auto getter and setter
     * */
    public int getMachineId() {
        return machineId;
    }
    //**sets the machineid for the part
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}

/**Part gets into InHouse. */
// super is the partClass being called,add machine id, super constructor has to be called first
//constructor calls super