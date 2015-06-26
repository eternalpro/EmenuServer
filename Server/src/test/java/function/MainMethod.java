package function;

import function.abs.Parent;

/**
 * Created by gefangshuai on 2015/6/25.
 */
public class MainMethod {

    public static void main(String[] args) {
        Parent p1 = new CA();
        Parent p2 = new CB();

        p1.doSomething("aaa");
        p2.doSomething();

    }
}
