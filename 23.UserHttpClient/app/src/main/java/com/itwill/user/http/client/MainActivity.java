package com.itwill.user.http.client;

import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.EditText;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;

import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {
    public static final String USER_WRITE =
            "http://192.168.12.31/user_mobile/mobile/user_write_action.jsp";
    public static final String USER_LOGIN =
            "http://192.168.12.31/user_mobile/mobile/user_login_action.jsp";
    public static final String USER_LIST =
            "http://192.168.12.31/user_mobile/mobile/user_list.jsp";
    public static final String USER_VIEW =
            "http://192.168.12.31/user_mobile/mobile/user_view.jsp";
    public static final String USER_LOGOUT =
            "http://192.168.12.31/user_mobile/mobile/user_logout_action.jsp";

    Button writeB;
    Button loginB;
    Button listB;
    Button viewB;
    Button logoutB;
    EditText resultET;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        writeB = (Button) findViewById(R.id.writeB);
        loginB = (Button) findViewById(R.id.loginB);
        listB = (Button) findViewById(R.id.listB);
        viewB = (Button) findViewById(R.id.viewB);
        logoutB = (Button) findViewById(R.id.logoutB);
        resultET = (EditText) findViewById(R.id.resultEditText);

        /*************회원가입**************/
        writeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("------", "writttttttt");
                new Thread() {
                    @Override
                    public void run() {
                        userWrite();
                    }
                }.start();
            }
        });
        /*************회원로그인**************/
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        userLogin();
                    }
                }.start();
            }
        });
        /*************회원리스트**************/
        listB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        userList();
                    }
                }.start();
            }
        });
        /*************회원상세보기**************/
        viewB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        userView();
                    }
                }.start();


            }
        });
        /*************회원로그아웃**************/
        logoutB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        userLogout();
                    }
                }.start();
            }
        });


    }

    Handler resultHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == RESULT_USER_WRITE) {
                String recvxmlStr = (String) msg.obj;
                resultET.setText(recvxmlStr);
                /*
                0: 성공
                1: 실패(중복아이디)
                 */

            } else if (msg.what == RESULT_USER_LOGIN) {
                String recvXml = (String) msg.obj;
                resultET.setText(recvXml);
                //DOM Parsing
                try {
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder domParser = dbf.newDocumentBuilder();
                    StringReader stringReader = new StringReader(recvXml);
                    InputSource inputSource = new InputSource(stringReader);
                    Document document = domParser.parse(inputSource);
                    NodeList statusNL =
                            document.getElementsByTagName("status");
                    Element stausE = (Element) statusNL.item(0);
                    String statusStr =
                            stausE.getFirstChild().getNodeValue();
                    String msgStr = document
                            .getElementsByTagName("msg")
                            .item(0)
                            .getFirstChild()
                            .getNodeValue();

                    if (statusStr.trim().equals("0")) {
                        //List Activity이동
                        //startActivity(null);
                        setTitle(msgStr);

                    } else if (statusStr.equals("1")) {
                        //AlertDialog보여주기
                        setTitle(msgStr);
                    } else if (statusStr.equals("2")) {
                        //AlertDialog보여주기
                        setTitle(msgStr);
                    } else if (statusStr.equals("9")) {
                        //AlertDialog보여주기
                        setTitle(msgStr);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                /*
                <result>
                    <status>0</status>
                    ------------------
                    0:성공
                    1:아이디없다
                    2.패쓰워드불일치
                    99:애로사항발생
                    ------------------
                    <msg>222 님로그인 성공 리스트화면으로 이동</msg>
                    <data>
                        <userList></userList>
                    </data>
                </result>
                */

            } else if (msg.what == RESULT_USER_LIST) {
                String recvXmlStr = (String) msg.obj;
                resultET.setText(recvXmlStr);
                /*
                - XML Parsing ArrayList<User> 얻기
                - Activity start
                - Intent에 ArrayList<User> 부쳐서 보내기
                - 실행된Activity 의 oncreat-->ListView에 보여주기
                 */


            } else if(msg.what==RESULT_USER_LOGOUT){
                String recvXmlStr = (String) msg.obj;
                resultET.setText(recvXmlStr);
            }
        }
    };
    /*********
     * message no
     **********/
    public final static int RESULT_USER_WRITE = 0;
    public final static int RESULT_USER_LOGIN = 1;
    public final static int RESULT_USER_LIST = 2;
    public final static int RESULT_USER_VIEW = 3;
    public final static int RESULT_USER_LOGOUT = 4;
    public final static int RESULT_USER_ERROR = 9;


    /*************회원로그아웃**************/
    private void userLogout(){
        try{
            URL url=new URL(USER_LOGOUT);
            HttpURLConnection httpURLConnection=
                    (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection
                    .setRequestProperty(
                            "Content-Type",
                            "application/x-www-form-urlencoded");
            /******CookieManager에저장된 쿠키값을 요청헤더에설정*******/
            CookieManager cookieManager=CookieManager.getInstance();
            String cookieString=
                    cookieManager.getCookie("http://192.168.12.31/user_mobile/mobile/");
            httpURLConnection.setRequestProperty("Cookie",cookieString);
            /**********************************************************/
            httpURLConnection.setDoInput(true);
            InputStream in=httpURLConnection.getInputStream();
            BufferedReader br=
                    new BufferedReader(
                            new InputStreamReader(in,"UTF-8"));
            StringBuffer sb = new StringBuffer();
            while (true) {
                String readLine = br.readLine();
                if (readLine == null) break;
                sb.append(readLine + "\n");
            }
            br.close();
            in.close();
            String write_result_xml = sb.toString();
            Message msg = new Message();
            msg.what = RESULT_USER_LOGOUT;
            msg.obj = write_result_xml;
            resultHandler.sendMessage(msg);
            Log.e("xmmml", write_result_xml);

            /******cookie 제거********/
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                CookieManager.getInstance().removeSessionCookies(null);
                //CookieManager.getInstance().removeAllCookies();
                CookieManager.getInstance().flush();

            }else{

                CookieSyncManager cookieSyncManager=
                        CookieSyncManager.createInstance(getApplicationContext());
                cookieSyncManager.stopSync();
            }

            /*************************/

        }catch (Exception e){
            e.printStackTrace();
            Message msg = new Message();
            msg.what = RESULT_USER_ERROR;
            msg.obj = e;
            resultHandler.sendMessage(msg);
        }
    }
    /*************회원상세보기**************/
    private void userView(){
        try {
            URL url = new URL(USER_VIEW);

        }catch (Exception e){

        }
    }
    /************* 회원리스트 **************/
    private void userList() {
        try {
            URL url = new URL(USER_LIST);
            HttpURLConnection httpURLConnection =
                    (HttpURLConnection) url.openConnection();
            //요청방식설정(요청라인)
            httpURLConnection.setRequestMethod("GET");
            //요청헤더값설정
            httpURLConnection
                    .setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            /********CookieManager에저장한 쿠키값을 요청헤더에설정****/
            CookieManager cookieManager = CookieManager.getInstance();
            String readCookieStr = cookieManager
                    .getCookie("http://192.168.12.31/user_mobile/mobile/");
            //Log.e("read cookie",readCookieStr);
            httpURLConnection.setRequestProperty("Cookie", readCookieStr);

            /**********************************************************/
            //서버에서데이타수신설정
            //httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            InputStream in =
                    httpURLConnection.getInputStream();
            BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(in, "UTF-8"));
            StringBuffer sb = new StringBuffer();
            while (true) {
                String readLine = br.readLine();
                if (readLine == null) break;
                sb.append(readLine + "\n");
            }
            br.close();
            in.close();
            String write_result_xml = sb.toString();
            Message msg = new Message();
            msg.what = RESULT_USER_LIST;
            msg.obj = write_result_xml;
            resultHandler.sendMessage(msg);
            Log.e("xmmml", write_result_xml);
            //연결해제
            httpURLConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
            Message msg = new Message();
            msg.what = RESULT_USER_ERROR;
            msg.obj = e;
            resultHandler.sendMessage(msg);
            //Log.e("errrrrorr",e.getMessage());
        }
    }

    /*************
     * 회원로그인
     **************/
    private void userLogin() {
        try {
            URL url = new URL(USER_LOGIN);
            /*
            Http연결객체
             */
            HttpURLConnection httpURLConnection =
                    (HttpURLConnection) url.openConnection();
            //요청방식설정(요청라인)
            httpURLConnection.setRequestMethod("POST");
            //요청헤더값설정
            httpURLConnection
                    .setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //서버로데이타전송설정
            httpURLConnection.setDoOutput(true);
            //서버에서데이타수신설정
            httpURLConnection.setDoInput(true);
            //요청바디(POST)-->파라메타설정
            String params =
                    "userId=222&password=222";
            OutputStream outputStream =
                    httpURLConnection.getOutputStream();
            outputStream.write(params.getBytes("UTF-8"));
            outputStream.close();

            InputStream in =
                    httpURLConnection.getInputStream();
            BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(in, "UTF-8"));
            StringBuffer sb = new StringBuffer();
            while (true) {
                String readLine = br.readLine();
                if (readLine == null) break;
                sb.append(readLine + "\n");
            }
            br.close();
            in.close();
            String write_result_xml = sb.toString();
            Message msg = new Message();
            msg.what = RESULT_USER_LOGIN;
            msg.obj = write_result_xml;
            resultHandler.sendMessage(msg);
            Log.e("xmmml", write_result_xml);
            /***************로그인세션쿠키유지***********/
            //1.응답쿠키값얻기
            Map<String, List<String>> headerFieldMap =
                    httpURLConnection.getHeaderFields();
            List<String> cookiesHeader = headerFieldMap.get("Set-Cookie");
            //2-1.응답쿠키저장객체생성(CookieManager)
            CookieManager cookieManager = CookieManager.getInstance();

            for (String cookieStr : cookiesHeader) {
                //JSESSIONID=CF6B312CD02D1B486C9A12070C251CF2; Path=/user_mobile; HttpOnly
                String cookieName =
                        HttpCookie.parse(cookieStr).get(0).getName();
                String cookieValue =
                        HttpCookie.parse(cookieStr).get(0).getValue();
                Log.e("cookieName:", cookieName);
                Log.e("cookieValue:", cookieValue);
                String saveCookieStr = cookieName + "=" + cookieValue;
                //2-2.응답쿠키저장(CookieManager)
                cookieManager
                        .setCookie(
                                "http://192.168.12.31/user_mobile/mobile/",
                                saveCookieStr);
                httpURLConnection.disconnect();
                /******************************************************/
            }

        } catch (Exception e) {
            e.printStackTrace();
            Message msg = new Message();
            msg.what = RESULT_USER_ERROR;
            msg.obj = e;
            resultHandler.sendMessage(msg);
            //Log.e("errrrrorr",e.getMessage());
        }
    }

    /*************
     * 회원가입
     **************/
    private void userWrite() {
        try {
            URL url = new URL(USER_WRITE);
            /*
            Http연결객체
             */
            HttpURLConnection httpURLConnection =
                    (HttpURLConnection) url.openConnection();
            //요청방식설정(요청라인)
            httpURLConnection.setRequestMethod("POST");
            //요청헤더값설정
            httpURLConnection
                    .setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //서버로데이타전송설정
            httpURLConnection.setDoOutput(true);
            //서버에서데이타수신설정
            httpURLConnection.setDoInput(true);
            //요청바디(POST)-->파라메타설정
            String params =
                    "userId=222&password=222&name=김&email=xxx@gmail.com";
            OutputStream outputStream =
                    httpURLConnection.getOutputStream();
            outputStream.write(params.getBytes("UTF-8"));
            outputStream.close();

            InputStream in =
                    httpURLConnection.getInputStream();
            BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(in, "EUC-KR"));
            StringBuffer sb = new StringBuffer();
            while (true) {
                String readLine = br.readLine();
                if (readLine == null) break;
                sb.append(readLine + "\n");
            }
            br.close();
            in.close();
            String write_result_xml = sb.toString();
            Message msg = new Message();
            msg.what = RESULT_USER_WRITE;
            msg.obj = write_result_xml;
            resultHandler.sendMessage(msg);

            //Log.e("xmmml",write_result_xml);
            /*
              <result>
                <status>0</status>
                <msg>회원가입성공 로그인화면보여줄께요!!</msg>
                <data>
                    <userList></userList>
                </data>
              </result>

              <result>
                <status>1</status>
                <msg>000 는 이미존재하는 아이디입니다.</msg>
                <data>
                <userList></userList>
                </data>
              </result>
             */


        } catch (Exception e) {
            e.printStackTrace();
            Message msg = new Message();
            msg.what = RESULT_USER_ERROR;
            msg.obj = e;
            resultHandler.sendMessage(msg);
            //Log.e("errrrrorr",e.getMessage());
        }
    }





}
