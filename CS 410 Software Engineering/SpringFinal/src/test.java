import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class test {
	private StringCalculator calc;
	
    @BeforeEach
    public void setUp() {
        calc = new StringCalculator();
    }
    
    @AfterEach
    public void tearDown() {
        calc = null;
    }

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
