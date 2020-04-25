import com.nmzl.pigpriceplatform.service.ServerInfoService;
import com.nmzl.pigpriceplatform.service.impl.ServerInfoServiceImpl;
import com.nmzl.pigpriceplatform.util.ServerInfo;
import com.nmzl.pigpriceplatform.util.ServerInfoBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Field;

@RunWith(JUnit4.class)
public class testServerInfo {

    @Test
    public void getServerInfo() throws IllegalAccessException, InterruptedException {
        ServerInfo serverInfo = new ServerInfo();
        Class<ServerInfoBean> clz = ServerInfoBean.class;
        Field[] fields = clz.getDeclaredFields();
        ServerInfoBean bean = serverInfo.getServerInfo();
        //System.out.println(serverInfo.printlnCpuInfo());
        for (Field field : fields){
            field.setAccessible(true);
            if(field.get(bean) != null)
                System.out.println(field.getName() + " : " + field.get(serverInfo.getServerInfo()));
        }

    }
}
