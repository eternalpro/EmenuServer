package function;

import function.abs.Parent;

/**
 * Created by gefangshuai on 2015/6/25.
 */
public class CB extends Parent{
    @Override
    public void doSomething(String param) {

    }

    @Override
    public void doSomething() {
        String p = m1();
        System.out.println(p);
    }
}
