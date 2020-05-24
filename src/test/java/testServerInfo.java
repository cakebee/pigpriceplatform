import com.nmzl.pigpriceplatform.service.ServerInfoService;
import com.nmzl.pigpriceplatform.service.impl.ServerInfoServiceImpl;
import com.nmzl.pigpriceplatform.util.ServerInfo;
import com.nmzl.pigpriceplatform.util.ServerInfoBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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

    public List<String> readCsv(String path) throws IOException {
        StringBuffer buffer = new StringBuffer();
        FSDataInputStream fsr = null;
        BufferedReader bufferedReader = null;
        String line;
        List<String> stringList = new ArrayList<>();
        try {
            FileSystem fs = FileSystem.get(URI.create(path), new Configuration());
            fsr = fs.open(new Path(path));
            bufferedReader = new BufferedReader(new InputStreamReader(fsr));
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                stringList.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return stringList;
    }

    @Test
    public void convertData() throws IOException {

    }
}
