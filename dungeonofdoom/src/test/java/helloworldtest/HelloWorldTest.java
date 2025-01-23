package helloworldtest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.helloworld.HelloWorld;

public class HelloWorldTest {
    @Test
    public void testAddPass() {
        assertEquals("Hello World", HelloWorld.test());
    }
}
