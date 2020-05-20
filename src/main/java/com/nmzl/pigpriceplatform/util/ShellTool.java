package com.nmzl.pigpriceplatform.util;

import com.nmzl.pigpriceplatform.exception.ShellException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class ShellTool {
    public String runShell(String shellPath) throws ShellException {
        try {
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
            System.out.print(sb.toString());
            return sb.toString();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            //TODO 字符串替换
            throw new ShellException("文件IO错误或中断错误");
        }
    }
}
