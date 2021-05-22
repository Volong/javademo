package github.io.ylongo;

public class SubjectImpl implements ISubject {

    @Override
    public void request() {
        System.out.println("具体的业务逻辑");
    }
}
