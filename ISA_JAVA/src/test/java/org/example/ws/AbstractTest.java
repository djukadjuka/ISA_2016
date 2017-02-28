package org.example.ws;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.Isa2016Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Isa2016Application.class)
public abstract class AbstractTest {

	protected Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
}
