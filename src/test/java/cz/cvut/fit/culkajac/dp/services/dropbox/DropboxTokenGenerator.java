package cz.cvut.fit.culkajac.dp.services.dropbox;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.component.bean.config.model.BeanSwitchYardScanner;
import org.switchyard.component.bpm.config.model.BPMSwitchYardScanner;
import org.switchyard.component.camel.config.model.RouteScanner;
import org.switchyard.component.rules.config.model.RulesSwitchYardScanner;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.test.mixins.CDIMixIn;
import org.switchyard.test.mixins.HornetQMixIn;
import org.switchyard.transform.config.model.TransformSwitchYardScanner;

import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session.AccessType;
import com.dropbox.client2.session.WebAuthSession;
import com.dropbox.client2.session.WebAuthSession.WebAuthInfo;

import cz.cvut.fit.culkajac.dp.Constants;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = "src/main/webResources" + SwitchYardTestCaseConfig.SWITCHYARD_XML, scanners = {
		BeanSwitchYardScanner.class, RulesSwitchYardScanner.class, BPMSwitchYardScanner.class, TransformSwitchYardScanner.class,
		RouteScanner.class }, mixins = { CDIMixIn.class, HornetQMixIn.class  })
public class DropboxTokenGenerator {

	private static final String APP_KEY = Constants.Dropbox.APP_KEY;
	private static final String APP_SECRET = Constants.Dropbox.APP_SECRET;
	private static final AppKeyPair ACCESS_KEY_PAIR = new AppKeyPair(APP_KEY, APP_SECRET);

	WebAuthSession was = new WebAuthSession(ACCESS_KEY_PAIR, AccessType.APP_FOLDER);

	@Test
	public void authenticate() throws DropboxException, IOException {

		WebAuthInfo wai = was.getAuthInfo();

		System.out.println("URL");
		System.out.println(wai.url);
		System.out.println("PRESS ANY KEY TO CONTINUE");
		System.in.read();
		was.retrieveWebAccessToken(wai.requestTokenPair);
		System.out.println(String.format("ACCESS PAIR (KEY, SECRET): [%s, %s]",
				was.getAccessTokenPair().key,
				was.getAccessTokenPair().secret));

	}
}
