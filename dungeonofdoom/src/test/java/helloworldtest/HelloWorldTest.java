package helloworldtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import com.example.helloworld.HelloWorld;

public class HelloWorldTest {
    @Test
    public void testAddPass() {
        assertEquals("Hello World", HelloWorld.test());
    }

    @Test
    public void testMock(){
        try (MockedStatic<HelloWorld> mockedHelloWorld = mockStatic(HelloWorld.class)) {
            mockedHelloWorld.when(HelloWorld::test).thenReturn("Mocked Hello World");

            assertEquals("Mocked Hello World", HelloWorld.test());

            mockedHelloWorld.verify(HelloWorld::test, times(1));
        }
    }
}
