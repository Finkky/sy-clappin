package cz.cvut.fit.culkajac.dp.services.delete;

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

import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = "src/main/webResources" + SwitchYardTestCaseConfig.SWITCHYARD_XML, scanners = {
		BeanSwitchYardScanner.class, RulesSwitchYardScanner.class, BPMSwitchYardScanner.class, TransformSwitchYardScanner.class,
		RouteScanner.class }, mixins = { CDIMixIn.class, HornetQMixIn.class })
public class DeleteServiceTest {

	@ServiceOperation("DeleteFileService.process")
	private Invoker doStore;

	@Test
	public void testDelete() {

		FileDescriptorDTO fd = new FileDescriptorDTO("text.jpg");

		fd.setExtension("jpeg");

		Object o = this.doStore.sendInOut(fd).getContent();

		System.out.println(o);
	}

	@Test
	public void testDeleteDocumentText() {

		FileDescriptorDTO fd = new FileDescriptorDTO("hakuna.txt");

		Object o = this.doStore.sendInOut(fd).getContent();

		System.out.println(o);
	}
}
