import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nmzl.pigpriceplatform.PigpriceplatformApplication;
import com.nmzl.pigpriceplatform.entity.User;
import com.nmzl.pigpriceplatform.service.UserService;
import com.nmzl.pigpriceplatform.service.impl.UserServiceImpl;
import com.nmzl.pigpriceplatform.util.JWTUtil;
import javafx.application.Application;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author : zxy
 * @date : 2020/4/3 15:05
 */
/*@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PigpriceplatformApplication.class)*/
public class testAuthentication {
    DefaultSecurityManager defaultSecurityManager;
    UsernamePasswordToken token;
    SimpleAccountRealm simpleAccountRealm;
    IniRealm iniRealm;
    Logger logger = LoggerFactory.getLogger(testAuthentication.class);

    @Autowired
    UserServiceImpl userService;

    @Test
    public void testIniRealm() {
        iniRealm = new IniRealm();
    }


    @Test
    public void addUser() {
        simpleAccountRealm.addAccount("A", "123", "admin");
    }

    @Test
    public void test1() {
        defaultSecurityManager = new DefaultSecurityManager();
        simpleAccountRealm = new SimpleAccountRealm();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();


        addUser();
        token = new UsernamePasswordToken("A", "123");
        subject.login(token);

        System.out.println(subject.isAuthenticated());

        subject.checkRole("admin");

        subject.logout();
        System.out.println(subject.isAuthenticated());

    }

    @Test
    public void getUser() {
        String token = JWTUtil.sign("root", "123");
        System.out.println(JWTUtil.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1ODc5OTU4NjQsInVzZXJuYW1lIjoicm9vdCJ9.3e77uheXZ4BU0Gyv0IZ48JE_tlMC3SDeCH23m2rUPIU", "root", "123"));

    }
}
