package github.io.ylongo;

public class SubjectProxy implements ISubject {

    private final ISubject subject;

    public SubjectProxy(ISubject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {

        System.out.println("做一些前置处理");
        subject.request();
        System.out.println("做一些后置处理");
    }
}

