package notification;

import data.Constant;
import data.PropertiesDataHandler;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

/**
 * @author fdn-faiz on 29/12/22
 * @project api-automation-beautyreview-kotlin
 */
public class TelegramNotificationSender implements ITestListener {
    private Long startTime;
    private String methodName;
    private final String filePath = System.getProperty("user.dir")+"/src/main/resources/requestHttp/";
    private final String envTest = System.getProperty("environment");
    String tokenBOT;
    Long channelID;

    @Override
    public void onTestStart(ITestResult result) {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        methodName = result.getMethod().getMethodName();
        System.out.println(methodName+" successfully executed!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Long endTime = System.currentTimeMillis();
        Long execTime = endTime - startTime;
        String className;

        methodName = result.getMethod().getMethodName();
        className = result.getTestClass().getRealClass().getName();
        String errorMessage = result.getThrowable().getMessage();

        sendNotification(execTime, methodName, errorMessage, className);
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }

    private void sendNotification(Long execTime, String methodName, String errorMessage, String className) {
        if (envTest.equals("prod")) {
            tokenBOT = new PropertiesDataHandler().loadPropsValue("TOKEN_TELEGRAM_PROD");
            channelID = Constant.Telegram.CHANNEL_PROD;
        } else {
            tokenBOT = new PropertiesDataHandler().loadPropsValue("TOKEN_TELEGRAM_STAG");
            channelID = Constant.Telegram.CHANNEL_STAG;
        }

        String content = className +"\n"+
                "Endpoint : "+ methodName +"\n"+
                "Error : "+ errorMessage +"\n"+
                "Execution Time : "+execTime+" ms"+"\n\n"+
                "Tolong di cek @hanherb @rusdi1298 @satyafariz @didimajdi @Ramadhanhasan @ditaisyiyah";

        RestAssured.baseURI = "https://api.telegram.org/bot"+tokenBOT+"/sendDocument";
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Content-Type", "multipart/form-data")
                .multiPart("chat_id", channelID)
                .multiPart("document", new File(filePath+methodName+".txt"))
                .multiPart("caption", content);

        Response response = requestSpecification.get(RestAssured.baseURI);

        System.out.println("Telegram Send Message Status Code : "+response.getStatusCode());
        System.out.println("---------------------------------------");
    }
}
