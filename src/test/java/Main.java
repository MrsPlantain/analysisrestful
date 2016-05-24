import net.sf.json.JSONObject;
import org.junit.Test;

/**
 * Created by chao on 2016/5/17.
 */
public class Main {

    @Test
    public  void test(){
        X x = new X();
        x.setA("a");
        x.setB("b");
        x.setC("c");
        JSONObject jsonObject = JSONObject.fromObject(x);
        System.out.println(jsonObject);
    }
}
