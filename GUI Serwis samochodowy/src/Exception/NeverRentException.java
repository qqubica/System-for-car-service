package Exception;

public class NeverRentException extends Throwable{
    @Override
    public String getMessage() {
        return "Never Rent Exception";
    }
}
