package module1.pract7session;

//rectangle object for soccer field
public class RecentageWithException {
    // @TODO rules
    // length and width not positive number
    // area > 10000 throw general exception

    //base attributes
    private double length;
    private double width;

    //@TODO handle exception
    public void setLength  (double length) throws IllegalArgumentException {
        if (length <= 0) {
            //invisible try catch 
            //--> catching illegal inputs with a custom message
            throw new IllegalArgumentException("Length must be positive");
        }
        this.length = length;
    }

    public double getLength(){return length;}

    //@TODO handle exception
    public void setWidth(double width) throws IllegalArgumentException{
        if(width <= 0){
            throw new IllegalArgumentException("Width must be positive");
        }
        this.width = width;
    }

    public double getWidth(){return width;} 

    //@TODO handle exception
    public double getArea(){// throws Exception{
        double area = length * width;
        if(area > 10000){
          //  throw new Exception("Area must be less than 10000");
        }
        return length * width;
    } 

}
