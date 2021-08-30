package DataProviders;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import Pojo.UserAccount;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class UsersProvider {
    @DataProvider(name = "getUsersData")
    private Object[][] getUsersData(){
        return new Object[][]{
                {new UserAccount("gabriela.marin.chaves@ucreativa.com", "asdgk", true)},
                {new UserAccount("gabriela.marin.chaves@ucreativa.com", "1313", false)}
        };
    }

    @DataProvider(name = "getUsersDataFromJson")
    private Object[][] getUsersDataFromJson() throws FileNotFoundException {
        JsonElement jsonData = new JsonParser().parse(new FileReader("src/test/resources/TestData/users.json"));
        JsonElement dataSet = jsonData.getAsJsonObject().get("dataSet");
        List<UserAccount> testData = new Gson().fromJson(dataSet, new TypeToken<List<UserAccount>>() {}.getType());
        Object[][] returnValue = new Object[testData.size()][1];
        int index = 0;
        for (Object[] each : returnValue) {
            each[0] = testData.get(index++);
        }
        return returnValue;
    }
}
