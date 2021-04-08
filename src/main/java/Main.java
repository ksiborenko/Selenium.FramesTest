import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.Assert.assertEquals;

public class Main {

    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.edgedriver().setup();
        this.driver = new EdgeDriver();
    }

    @Test
    public void frame() {
        this.driver.get("https://the-internet.herokuapp.com/nested_frames");
        this.driver.switchTo().frame("frame-top");
        this.driver.switchTo().frame("frame-left");
        assertEquals("LEFT", this.driver.findElement(By.tagName("body")).getText());
        this.driver.switchTo().parentFrame();
        this.driver.switchTo().frame("frame-middle");
        assertEquals("MIDDLE", this.driver.findElement(By.tagName("body")).getText());
        this.driver.switchTo().parentFrame();
        this.driver.switchTo().frame("frame-right");
        assertEquals("RIGHT", this.driver.findElement(By.tagName("body")).getText());
        this.driver.switchTo().defaultContent();
        this.driver.switchTo().frame("frame-bottom");
        assertEquals("BOTTOM", this.driver.findElement(By.tagName("body")).getText());
    }
    @After
    public void end () {
        this.driver.close();
    }
}
