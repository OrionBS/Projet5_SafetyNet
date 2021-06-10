package fr.orionbs.SafetyNet.exceptionTest;

import fr.orionbs.SafetyNet.exception.ErrorDTO;
import fr.orionbs.SafetyNet.exception.ExceptionInterceptor;
import fr.orionbs.SafetyNet.exception.MissingParamException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ExceptionInterceptorTest {

    @Autowired
    ExceptionInterceptor exceptionInterceptor;

    @Test
    public void testHandler() {
        //GIVEN
        MissingParamException missingParamException = new MissingParamException("Info");

        //WHEN
        ErrorDTO errorDTO = exceptionInterceptor.handler(missingParamException);

        //THEN
        assertThat(errorDTO).isNotNull();
        assertThat(errorDTO.getMessage()).isEqualTo("Info");
    }

}
