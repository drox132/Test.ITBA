package exception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(){
        super("La categoria no existe");
    }
}
