package by.bsuir.java.client.exception;

public class NegativeCostException extends Exception {
    public NegativeCostException(){
        super();
    }

    public NegativeCostException(String message){
        super(message);
    }

    public NegativeCostException(Exception e){
        super(e);
    }

    public NegativeCostException(String message, Exception e){
        super(message,e);
    }
}

