package com.aiplayer.learning.leetcode.client;

import com.aiplayer.learning.leetcode.client.exception.InitException;
import com.aiplayer.learning.leetcode.client.exception.InnerException;
import com.aiplayer.learning.leetcode.client.exception.InvalidAccountException;
import com.aiplayer.learning.leetcode.client.model.Problem;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;

public class LeetcodeAPIs {

    private HttpClient client;
    private String csrftoken;

    public LeetcodeAPIs() throws InitException {
        Authenticator authenticator=new Authenticator() {
        };
        try {
            client= HttpClient.newBuilder()
                    .authenticator(authenticator)//配置authenticator
                    .sslContext(SSLContext.getDefault())//配置 sslContext
                    .sslParameters(new SSLParameters())//配置 sslParameters
                    .proxy(ProxySelector.getDefault())//配置 proxy
                    .executor(Executors.newCachedThreadPool())//配置 executor
                    .followRedirects(HttpClient.Redirect.ALWAYS)//配置 followRedirects
                    .version(HttpClient.Version.HTTP_1_1)//配置 version
                    .build();
            //CookieManager.getDefault().get(URI.create(URLEnum.ROOT)).get("csrftoken");
            CookieManager manager=new CookieManager();
            manager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
            CookieHandler.setDefault(manager);
            URL url=new URL(URLEnum.ROOT_ROOT);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.getHeaderFields();
            CookieStore store = manager.getCookieStore();
            List<HttpCookie> lCookies=store.getCookies();
            for (HttpCookie cookie: lCookies) {
                if("csrftoken".equals(cookie.getName())) {
                    csrftoken = cookie.getValue();
                }
            }
            if(csrftoken == null) {
                throw new InitException();
            }
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new InitException();
        }
    }


    public void login(String user, String password) throws InvalidAccountException {
        try {
            String formData = "csrfmiddlewaretoken=" + URLEncoder.encode(this.csrftoken,"UTF-8") +
                    ",login=" + URLEncoder.encode(user,"UTF-8") +
                    ",password=" + URLEncoder.encode(password,"UTF-8") +
                    ",remember=" + URLEncoder.encode("on","UTF-8") +
                    ",next=problems";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URLEnum.LOGIN))
                    .header("User-Agent", URLEnum.USER_AGENT)
                    .header("Content-Type", "multipart/form-data; boundary={"+ UUID.randomUUID().toString() +"}")
                    .header("Referer", URLEnum.LOGIN)
                    .POST(HttpRequest.BodyPublishers.ofString(formData))
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();
            HttpResponse<String> r = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(r.toString());
        } catch (IOException | InterruptedException e) {
            throw new InvalidAccountException();
        }
    }

    public List<Problem> getProblems() throws InnerException {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URLEnum.PROBLEMS))
                    .GET()
                    .header("User-Agent", URLEnum.USER_AGENT)
                    .headers("accept-language","zh-CN,zh;q=0.9,en;q=0.8")
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();
            HttpResponse<String> r = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject allProblemJsonObj = JSON.parseObject(r.body());
            JSONArray allProblemArray = allProblemJsonObj.getJSONArray("stat_status_pairs");
            JSONObject problemJsonObj;
            JSONObject statObj;
            List<Problem> problems = new ArrayList<Problem>();
            Problem problem;
            for(int i = 0; i < allProblemArray.size(); i++) {
                problemJsonObj = allProblemArray.getJSONObject(i);
                problem = new Problem();
                problem.setLevel(Problem.Level.fromLevel(problemJsonObj.getJSONObject("difficulty").getInteger("level")));
                statObj = problemJsonObj.getJSONObject("stat");
                problem.setSeqNum(statObj.getInteger("question_id"));
                problem.setQuestionName(statObj.getString("question__title"));
                problem.setQuestionUrl(URLEnum.PROBLEM_PREFIX+statObj.getString("question__title_slug"));
                System.out.println(problem.getSeqNum()+" " + problem.getLevel().name + " " + problem.getQuestionName());
            }
            return null;
        } catch (IOException | InterruptedException e) {
            throw new InnerException();
        }
    }

    public static void main(String[] args) {

        try {
            LeetcodeAPIs apis = new LeetcodeAPIs();
            //apis.login("18616830015", "asdf@1234");
            apis.getProblems();
        //} catch (InvalidAccountException | InitException | InnerException e) {
        } catch (InitException | InnerException e) {
            e.printStackTrace();
        }
    }
}
