package ch.makery.address.view;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VerifyandHashTest {
	
	@Test
	@DisplayName("Test correct password")
	void VerifyPasswordTestCorrect() {
    	//Testing with password of "Bobson"
    	String testPW = "JpI0SQXX/7JnWVve192OUA==";
    	String testSalt = "YTaJ9AHTD4HPRg==";
    	
    	assertTrue(LoginController.verifyPassword("Bobson",testPW,testSalt));
	}
	
	@Test
	@DisplayName("Test incorrect password")
	void VerifyPasswordTestIncorrect() {
    	//Testing with password of "Bobson"
    	String testPW = "JpI0SQXX/7JnWVve192OUA==";
    	String testSalt = "YTaJ9AHTD4HPRg==";
    	
    	assertFalse(LoginController.verifyPassword("jobson",testPW,testSalt));
	}
	
	@Test
	@DisplayName("Test of makeHash method")
	void makeHashTest() {
		String testSalt = "YTaJ9AHTD4HPRg==";
		
		Optional <String> Hash =  Optional.of("JpI0SQXX/7JnWVve192OUA==");
		Optional <String> createdHash = LoginController.makeHash("Bobson", testSalt);
		
		assertEquals(Hash,createdHash);
	}


}
