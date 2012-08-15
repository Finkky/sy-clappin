package cz.cvut.fit.culkajac.dp.services.amazons3;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Map.Entry;

import javax.activation.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.Message;
import org.switchyard.component.bean.config.model.BeanSwitchYardScanner;
import org.switchyard.component.bpm.config.model.BPMSwitchYardScanner;
import org.switchyard.component.camel.config.model.RouteScanner;
import org.switchyard.component.rules.config.model.RulesSwitchYardScanner;
import org.switchyard.test.Invoker;
import org.switchyard.test.ServiceOperation;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.test.mixins.CDIMixIn;
import org.switchyard.test.mixins.HornetQMixIn;
import org.switchyard.transform.config.model.TransformSwitchYardScanner;

import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import cz.cvut.fit.culkajac.dp.Utils;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.GetObject;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.GetObjectResponse;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = "src/main/webResources" + SwitchYardTestCaseConfig.SWITCHYARD_XML, scanners = {
		BeanSwitchYardScanner.class, RulesSwitchYardScanner.class, BPMSwitchYardScanner.class, TransformSwitchYardScanner.class,
		RouteScanner.class }, mixins = { CDIMixIn.class, HornetQMixIn.class })
public class AmazonS3Test {

	@ServiceOperation("SecurityAdapter.sign")
	private Invoker doSign;

	@ServiceOperation("SecurityAdapter/AmazonS3.GetObject")
	private Invoker doGetObject;

	@ServiceOperation("AmazonS3ApiAdapter.getObject")
	private Invoker doGetObjectApi;

	// @Test
	public void authenticate() {
		GetObject r = new GetObject();

		r.setBucket("clappin2");
		r.setGetData(true);
		r.setInlineData(true);
		r.setGetMetadata(true);
		r.setKey("text.txt");

		System.out.println(r);

		GetObject signed = this.doSign.sendInOut(r).getContent(GetObject.class);

		System.out.println(signed);

		Message m = this.doGetObject.sendInOut(signed);

		for (Entry<String, DataSource> entry : m.getAttachmentMap().entrySet()) {
			System.out.println(String.format("%s : %s", entry.getKey(), entry.getValue()));

		}

		GetObjectResponse n = m.getContent(GetObjectResponse.class);

		try {
			System.out.println(n.toString());
			System.out.println(new String(n.getGetObjectResponse().getData(), "UTF-8"));
			System.out.println(n.getGetObjectResponse().getMetadata().get(0).getValue());

		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

	}

	@Test
	public void s3ApiTest() throws IOException {
		GetObjectRequest r = new GetObjectRequest("clappin2", "text.txt");
		S3Object s = this.doGetObjectApi.sendInOut(r).getContent(S3Object.class);

		Reader rder = new InputStreamReader(s.getObjectContent());

		char[] data = new char[Utils.safeLongToInt(s.getObjectMetadata().getContentLength())];
		rder.read(data);

		System.out.println(new String(data));

	}
}
