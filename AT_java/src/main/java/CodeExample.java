
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import static java.nio.charset.StandardCharsets.UTF_8;


/**
 * Класс для работы с JSON из Allure для получения детальной информации по тестам в сборках
 */


public class CodeExample extends JenkinsIntegration {
    String buildName;
    String runNum;

    CodeExample(String buildName, String runNum) {
        this.buildName = buildName;
        this.runNum = runNum;
    }

    public String getBuildName() {
        return buildName;
    }

    public String getRunNum() {
        return runNum;
    }


    //Метод возвращает краткую информацию по всем тестам из Suite в Аллюре
    public List<HashMap> getAllTestsShortInfo(Map<String, LinkedHashMap> jsonValues) {
        List<HashMap> allTests = new ArrayList<>();
        for (HashMap item : (List<HashMap>) jsonValues.get("children")) {
            List<HashMap> testInSuite = (List<HashMap>) item.get("children");
            allTests.addAll(testInSuite);
        }
        return allTests;
    }


    //метод возвращает список тестов (объектов касса TestCaseInfo) в зависимости от желаемого статуса
    //статусы: all, failed, passed
    public List<TestCaseInfo> getTestsObjByStatus(List<HashMap> allTests, String status, String BUILD, String buildRunNum) {
        List<TestCaseInfo> testObjs = new ArrayList<>();
        for (HashMap test : allTests) {
            if (test.get("status").equals(status)) {
                TestCaseInfo newTestObj = makeTestObj(test, BUILD, buildRunNum);
                testObjs.add(newTestObj);
            }
        }
        return testObjs;
    }

    //метод возвращает список с именем и номером теста
    private List<String> getTestNumAndName(String name) {
        List<String> nameNumDef = new ArrayList<>(Arrays.asList(name.split("[^\\d|а-яА-Я|a-zA-Z|\\p{P}]")));
        String testNum = nameNumDef.remove(0);
        String testName = String.join(",", nameNumDef).replaceAll(",", " ");
        List testNumAndName = new ArrayList();
        testNumAndName.add(0, testNum);
        testNumAndName.add(1, testName);
        return testNumAndName;
    }


    private List<String> getImageAndTextInTestJson(List<HashMap> attachments, String BUILD, String buildRunNum) {
        List<String> imageAndTxtURL = new ArrayList<>();
        for (HashMap step : attachments) {
            if (step.containsValue("image/png")) {
                String screenShotURL = "http://212.11.151.160/job/" + BUILD + "/" + buildRunNum + "/allure/data/attachments/" + step.get("source");
                imageAndTxtURL.add(0, screenShotURL);
            }
            if (step.containsValue("text/plain")) {
                String txtURL = "http://212.11.151.160/job/" + BUILD + "/" + buildRunNum + "/allure/data/attachments/" + step.get("source");
                imageAndTxtURL.add(1, txtURL);
            }
        }
        return imageAndTxtURL;
    }

}
