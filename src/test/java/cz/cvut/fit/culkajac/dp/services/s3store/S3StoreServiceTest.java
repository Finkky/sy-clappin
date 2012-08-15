package cz.cvut.fit.culkajac.dp.services.s3store;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
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

import cz.cvut.fit.culkajac.dp.Constants;
import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;
import cz.cvut.fit.culkajac.dp.dto.OperationStatusDTO;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = "src/main/webResources" + SwitchYardTestCaseConfig.SWITCHYARD_XML, scanners = {
		BeanSwitchYardScanner.class, RulesSwitchYardScanner.class, BPMSwitchYardScanner.class, TransformSwitchYardScanner.class,
		RouteScanner.class }, mixins = { CDIMixIn.class, HornetQMixIn.class })
public class S3StoreServiceTest {

	@ServiceOperation("S3StoreService.process")
	private Invoker doStore;

	@Test
	public void testS3StoreDocument() {

		FileDescriptorDTO fd = new FileDescriptorDTO("text.txt");
		fd.setExtension("txt");

		FileDTO f = new FileDTO(fd, "TEST".getBytes());

		OperationStatusDTO ex = new OperationStatusDTO(200, Constants.AmazonS3.ROUTE_ID);
		OperationStatusDTO o = this.doStore.sendInOut(f).getContent(OperationStatusDTO.class);

		Assert.assertEquals(ex, o);
	}
}
