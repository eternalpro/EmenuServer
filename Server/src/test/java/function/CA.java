package function;

import function.abs.Parent;

/**
 * Created by gefangshuai on 2015/6/25.
 */
public class CA extends Parent{

    @Override
    public void doSomething(String param) {
        String p = m1();
        System.out.println(p + ":" + param);
    }

    @Override
    public void doSomething() {

    }


}
