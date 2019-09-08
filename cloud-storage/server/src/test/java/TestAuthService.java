import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.cloud.storage.server.service.AuthService;

public class TestAuthService extends Assert {
    private AuthService authService = new AuthService();

    @BeforeClass
    public void connect() {
        authService.connect();
        authService.addTestAccountsToDb();
    }

    @DataProvider
    public Object[][] testDataForTestIsAccountActive() {
        return new Object[][]{
                {"login77@example.com", true},
                {"", false},
                {"login99@example.com", false}
        };
    }

    @Test(dataProvider = "testDataForTestIsAccountActive")
    public void testIsAccountActive(String login, boolean expected) {
        boolean actual = authService.isAccountActive(login);
        assertEquals(actual, expected);
    }

    @DataProvider
    public Object[][] testDataForTestIsLoginBusy() {
        return new Object[][]{
                {"login55@example.com", true},
                {"", false},
                {"login1000@example.com", false}
        };
    }

    @Test(dataProvider = "testDataForTestIsLoginBusy")
    public void testIsLoginBusy(String login, boolean expected) {
        boolean actual = authService.isLoginBusy(login);
        assertEquals(actual, expected);
    }

    @DataProvider
    public Object[][] testDataForTestIsLoginValid() {
        return new Object[][]{
                {null, false},
                {"", false},
                {"qieufqef", false},
                {"qi@euf@qef", false},
                {"qieufqef@", false},
                {"@qieufqef", false},
                {"aedaw@aead", false},
                {"aedaw@aead.ru", true},
                {"aedaw@aead.r.u", false},
                {"aedaw@.aead.ru", false},
                {"aedaw@.aeadru", false},
        };
    }

    @Test(dataProvider = "testDataForTestIsLoginValid")
    public void testIsLoginValid(String login, boolean expected) {
        boolean actual = authService.isLoginValid(login);
        assertEquals(actual, expected);
    }

    @DataProvider
    public Object[][] testDataForTestAuthOK() {
        return new Object[][]{
                {"", "Login43", null},
                {"login43@example.com", "", null},
                {"", "", null},
                {"login43@example.com", "Login43", "Vasiliy Ivanovich43"},
                {"login5@example.com", "Login5", "Vasiliy Ivanovich5"},
                {"login87@example.com", "Login87", "Vasiliy Ivanovich87"},
                {"login777@example.com", "Login777", null},
        };
    }

    @Test(dataProvider = "testDataForTestAuthOK")
    public void testAuthOK(String login, String pass, String expected) {
        String actual = authService.authOK(login, pass);
        assertEquals(actual, expected);
    }

    @AfterClass
    public void disconnect() {
        authService.disconnect();
    }
}
