package other;

/**defines an Outsourced part.
 * extends(subclass of) parts class
 * */
public class Outsourced extends Part {
    private String companyName;
    /**creates an outsourced part. */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        //super keyword calls the superclass method
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**returns the getCompanyName for the part. */
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName;}
}
