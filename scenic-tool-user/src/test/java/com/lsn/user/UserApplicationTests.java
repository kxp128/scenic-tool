package com.lsn.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApplicationTests {

	@Test
	public void contextLoads() {


		String sPackageName = CodeGenerator.class.getPackage().getName();

		System.out.println("----"+sPackageName);
	}

}
