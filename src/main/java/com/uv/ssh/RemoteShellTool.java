package com.uv.ssh;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class RemoteShellTool {

    private Connection conn;
    private String ipAddr;
    private String charset = Charset.defaultCharset().toString();
    private String userName;
    private String password;
    private boolean isLogin;

    public RemoteShellTool(String ipAddr, String userName, String password, String charset) {
        this.ipAddr = ipAddr;
        this.userName = userName;
        this.password = password;
        if (charset != null) {
            this.charset = charset;
        }
    }

    public boolean login() throws IOException {
        conn = new Connection(ipAddr);
        // 连接
        conn.connect();
        // 认证
        this.isLogin = conn.authenticateWithPassword(userName, password);
        return this.isLogin;
    }

    public void logout() {
        if (isLogin) {
            conn.close();
            isLogin = false;
        }
    }

    public String exec(String cmds) throws IOException {
        InputStream in;
        if (isLogin) {
            // 打开一个会话
            Session session = conn.openSession();
            session.execCommand(cmds);

            in = session.getStdout();
            String result = this.processStdout(in, this.charset);
            session.close();
            return result;
        }
        return null;
    }

    public String processStdout(InputStream in, String charset) {

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
