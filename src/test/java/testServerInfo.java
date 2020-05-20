import com.nmzl.pigpriceplatform.service.ServerInfoService;
import com.nmzl.pigpriceplatform.service.impl.ServerInfoServiceImpl;
import com.nmzl.pigpriceplatform.util.ServerInfo;
import com.nmzl.pigpriceplatform.util.ServerInfoBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    @Test
    public void runShell() {
        try {
            String shellPath = "/Users/zxy/Temp/test.sh";
            ProcessBuilder builder = new ProcessBuilder("/bin/chmod", "755", shellPath);
            Process process = builder.start();
            process.waitFor();

            Process ps = Runtime.getRuntime().exec(shellPath);
            ps.waitFor();

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            System.out.println(sb.toString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
