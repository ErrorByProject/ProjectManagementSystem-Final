package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void getUser() {
        Email email = new Email("user","host","domain");
        Email email1 = new Email("U@er","host","domain");
        Email email2 = new Email("Us1er","host","domain");
        Assertions.assertEquals("user",email.getUser());
        Assertions.assertEquals("wrong email",email1.getUser());
        Assertions.assertEquals("us1er",email2.getUser());
    }

    @Test
    void getHost() {
        Email email = new Email("user","host","domain");
        Email email1 = new Email("U@er","host@","domain");
        Email email2 = new Email("Us1er","host21","domain");
        Assertions.assertEquals("host",email.getHost());
        Assertions.assertEquals("wrong email",email1.getHost());
        Assertions.assertEquals("host21",email2.getHost());
    }

    @Test
    void getDomain() {
        Email email = new Email("user","host","domain");
        Email email1 = new Email("U@er","host@","domain@");
        Email email2 = new Email("Us1er","host21","domain115");
        Assertions.assertEquals("domain",email.getDomain());
        Assertions.assertEquals("wrong email",email1.getHost());
        Assertions.assertEquals("domain115",email2.getDomain());
    }


    @Test
    void testEquals() {
        Email email = new Email("user","host","domain");
        Email email1 = new Email("User","host1","domain");
        Assertions.assertEquals(false,email.equals(email1));
        Email email2 = new Email("user","host","domain");
        Assertions.assertEquals(true,email.equals(email2));
    }
}