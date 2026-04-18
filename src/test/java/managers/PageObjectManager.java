package managers;

import org.openqa.selenium.WebDriver;

import pageObjects.ArrayPage;
import pageObjects.DataStructuresPages;
import pageObjects.HomePages;
import pageObjects.LoginPage;
import pageObjects.QueuePage;
import pageObjects.RegisterPage;
import pageObjects.StackPage;
import pageObjects.TryEditorPage;

public class PageObjectManager {
	private WebDriver driver;
private RegisterPage registerPage;
    private LoginPage loginPage;
    private HomePages homePage;
    private ArrayPage arrayPage;
    private DataStructuresPages dataStructuresPage;
    private StackPage stackPage;
    private QueuePage queuePage;
    private TryEditorPage tryEditorPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }
    public RegisterPage getRegisterPage() {
        return (registerPage == null) ? registerPage = new RegisterPage() : registerPage;
    }

    public LoginPage getLoginPage() {
        return (loginPage == null) ? loginPage = new LoginPage() : loginPage;
    }

    public HomePages getHomePage() {
        return (homePage == null) ? homePage = new HomePages() : homePage;
    }

    public ArrayPage getArrayPage() {
        return (arrayPage == null) ? arrayPage = new ArrayPage() : arrayPage;
    }

    public DataStructuresPages getDataStructuresPage() {
        return (dataStructuresPage == null) ? dataStructuresPage = new DataStructuresPages() : dataStructuresPage;
    }

    public StackPage getStackPage() {
        return (stackPage == null) ? stackPage = new StackPage() : stackPage;
    }

    public QueuePage getQueuePage() {
        return (queuePage == null) ? queuePage = new QueuePage() : queuePage;
    }

    public TryEditorPage getTryEditorPage() {
        return (tryEditorPage == null) ? tryEditorPage = new TryEditorPage() : tryEditorPage;
    }}