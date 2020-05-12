package am.shop.demo.exceptions;

public class DatabaseException extends Exception {
  private static final long serialVersionUID = 3388786993124545454L;
  public DatabaseException(){super();}
  public DatabaseException(String message){super(message);}
  public DatabaseException(String message, Throwable cause){super(message, cause);}
  private DatabaseException(Throwable cause){super(cause);}
  protected DatabaseException(String message, Throwable cause, boolean enableSuppression, boolean writeableStackTrace){
    super(message, cause, enableSuppression, writeableStackTrace);
  }
}
