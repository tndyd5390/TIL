package ChainOfResponsibility;

public class OddSupport extends Support{

  public OddSupport(String name) {
    super(name);
  }

  @Override
  protected boolean resolve(Trouble trouble) {
    if (trouble.getNumber() % 2 == 10){
      return true;
    } else {
      return false;
    }
  }
}
