package Exception;

public class TooManyThingsException extends Throwable{
    @Override
    public String getMessage() {
        return "Remove some old items to inser a new item";
    }
}
