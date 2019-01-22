package com.uv.ssh;

import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.session.SessionChannelClient;
import com.sshtools.j2ssh.transport.HostKeyVerification;

import java.io.*;

/**
 * @author uvsun 2019/1/22 12:47 PM
 * 依赖:
 * <!-- https://mvnrepository.com/artifact/sshtools/j2ssh-core -->
 * <dependency>
 * <groupId>sshtools</groupId>
 * <artifactId>j2ssh-core</artifactId>
 * <version>0.2.9</version>
 * </dependency>
 * 功能:
 * ssh2登录远程服务器执行命令或者下载文件.但是执行命令或脚本无法得到echo输出返回
 */
public class SSHGetter {

    public SshClient getSshClient(String host, int port, String user, String password) {
        SshClient client = new SshClient();
        try {
            HostKeyVerification hostKeyVerification = (s, sshPublicKey) -> true;
            client.connect(host, port, hostKeyVerification);

            // 设置用户名和密码
            PasswordAuthenticationClient pwd = new PasswordAuthenticationClient();
            pwd.setUsername(user);
            pwd.setPassword(password);

            int result = client.authenticate(pwd);
            // 如果连接完成
            if (result == AuthenticationProtocolState.COMPLETE) {
                return client;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean execCmd(SshClient client, String cmd) throws IOException {
        boolean result;
        /**
         * 执行命令
         */
        SessionChannelClient scc = client.openSessionChannel();
        result = scc.executeCommand("ls");

        scc.close();
        return result;
    }

    public void getFile(SshClient client, String remoteFile, String localFile) throws IOException {


//
        /**
         * 拿文件
         */
        OutputStream os = new FileOutputStream(localFile);
        client.openSftpClient().get(remoteFile, os);
        File file = new File(localFile);
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
                    e1.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        SSHGetter getter = new SSHGetter();
        SshClient client = getter.getSshClient("192.168.1.204", 22, "root", "123456");
        getter.getFile(client, "/home/app/shmonitor/suspend.sh", "suspend.sh");
        getter.execCmd(client, "ls");
        client.disconnect();
    }
}
