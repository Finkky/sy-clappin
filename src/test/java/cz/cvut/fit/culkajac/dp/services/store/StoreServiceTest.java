package cz.cvut.fit.culkajac.dp.services.store;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
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

import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = "src/main/webResources" + SwitchYardTestCaseConfig.SWITCHYARD_XML, scanners = {
		BeanSwitchYardScanner.class, RulesSwitchYardScanner.class, BPMSwitchYardScanner.class, TransformSwitchYardScanner.class,
		RouteScanner.class }, mixins = { CDIMixIn.class, HornetQMixIn.class })
public class StoreServiceTest {

	@ServiceOperation("StoreFileService.process")
	private Invoker doStore;

	@Test
	public void testStoreDocument() throws IOException {

		FileDescriptorDTO fd = new FileDescriptorDTO("jamalx.jpg");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream is = new FileInputStream(new File("d:/jamalx.jpg"));
		IOUtils.copy(is, baos);
		is.close();

		fd.setExtension("jpg");
		FileDTO f = new FileDTO(fd, baos.toByteArray());

		Object o = this.doStore.sendInOut(f).getContent();

		System.out.println(o);
	}

	@Test
	public void testStoreDocumentText() {

		FileDescriptorDTO fd = new FileDescriptorDTO("hakuna.txt");
		FileDTO f = new FileDTO(fd, "matata".getBytes());

		Object o = this.doStore.sendInOut(f).getContent();

		System.out.println(o);
	}
}
