package module1;

public class Shapes {
    // attributes of Shapes class
    private String colour;
    private int noOfSides;

    // default Shapes object
    public Shapes() {

    }

    // Shapes object with data specified
    public Shapes(String colour, int noOfSides){
        this.colour = colour;
        this.noOfSides = noOfSides;
    }

    public String getColor() {
        return this.colour;
    }

    public int getNoOfSides() {
        return this.noOfSides;
    }

}
