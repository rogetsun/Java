package com.uv.ssh;

import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;

import java.io.*;

/**
 * @author uvsun 2019/1/22 12:47 PM
 */
public class SSHGetter {
    public void getMessage(String path) {
        String ip = "192.168.1.204";
        String catalogue = "";
        String filename = "/home/app/shmonitor/goon.sh";
        ip = path.substring(0, path.indexOf("#"));
        catalogue = path.substring(path.indexOf("#") + 1, path.lastIndexOf("/") + 1);
        filename = path.substring(path.lastIndexOf("/") + 1);
        SshClient client = new SshClient();
        try {
            client.connect(ip, 22);
            // 设置用户名和密码
            PasswordAuthenticationClient pwd = new PasswordAuthenticationClient();
            pwd.setUsername("root");
            pwd.setPassword("123456");
            int result = client.authenticate(pwd);
            if (result == AuthenticationProtocolState.COMPLETE) {// 如果连接完成
                OutputStream os = new FileOutputStream("./" + filename);
                client.openSftpClient().get(catalogue + filename, os);
                File file = new File("./" + filename);
                if (!file.exists()) {
                    file.createNewFile();
                }
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader(file));
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e1) {
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 这里的path是我自己约定的ip#catalogue的形式，
        // 如果形式不同，方法体截取的方法只需做对应的修改即可。
        String path = "192.168.1.204#/home/app/shmonitor/goon.sh";
        SSHGetter get = new SSHGetter();
        get.getMessage(path);
    }
}
