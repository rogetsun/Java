package com.uv.ssh;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 依赖:
 * <!-- https://mvnrepository.com/artifact/ch.ethz.ganymed/ganymed-ssh2 -->
 * <dependency>
 * <groupId>ch.ethz.ganymed</groupId>
 * <artifactId>ganymed-ssh2</artifactId>
 * <version>build209</version>
 * </dependency>
 * 功能:
 * ssh2登录远程服务器,执行脚本并得到脚本echo输出返回
 */

public class RemoteShellTool {

    private Connection connection;
    private String host;
    private String charset = Charset.defaultCharset().toString();
    private String userName;
    private String password;
    private boolean isLogin;

    public RemoteShellTool(String host, String userName, String password, String charset) {
        this.host = host;
        this.userName = userName;
        this.password = password;
        if (charset != null) {
            this.charset = charset;
        }
    }

    public boolean login() throws IOException {
        connection = new Connection(host);
        // 连接
        connection.connect();
        // 认证
        this.isLogin = connection.authenticateWithPassword(userName, password);
        return this.isLogin;
    }

    public void logout() {
        if (isLogin) {
            connection.close();
            isLogin = false;
        }
    }

    public String exec(String cmds) throws IOException {
        InputStream in;
        if (isLogin) {
            // 打开一个会话
            Session session = connection.openSession();
            session.execCommand(cmds);

            in = session.getStdout();
            String result = this.processStdout(in, this.charset);
            session.close();
            return result;
        }
        return null;
    }

    private String processStdout(InputStream in, String charset) {

        byte[] buf = new byte[1024];
        StringBuffer sb = new StringBuffer();
        try {
            while (in.read(buf) != -1) {
                sb.append(new String(buf, charset));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {

        RemoteShellTool tool = new RemoteShellTool("192.168.1.204", "root", "123456", "UTF-8");
        tool.login();
        String result = tool.exec("/home/app/t.sh");
        System.out.println(result);
        result = tool.exec("cat /home/app/t.sh");
        System.out.println(result);
        tool.logout();

    }

}
