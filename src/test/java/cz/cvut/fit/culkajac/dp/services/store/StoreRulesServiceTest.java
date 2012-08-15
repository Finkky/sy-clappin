package cz.cvut.fit.culkajac.dp.services.store;

import java.nio.charset.Charset;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.BaseHandler;
import org.switchyard.Exchange;
import org.switchyard.ExchangeHandler;
import org.switchyard.HandlerException;
import org.switchyard.Message;
import org.switchyard.component.bean.config.model.BeanSwitchYardScanner;
import org.switchyard.component.bpm.config.model.BPMSwitchYardScanner;
import org.switchyard.component.rules.config.model.RulesSwitchYardScanner;
import org.switchyard.test.Invoker;
import org.switchyard.test.ServiceOperation;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.test.mixins.CDIMixIn;
import org.switchyard.test.mixins.HornetQMixIn;
import org.switchyard.transform.config.model.TransformSwitchYardScanner;

import com.google.common.collect.Sets;

import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;
import cz.cvut.fit.culkajac.dp.dto.RoutableFileDTO;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = "src/main/webResources" + SwitchYardTestCaseConfig.SWITCHYARD_XML, scanners = {
		BeanSwitchYardScanner.class, RulesSwitchYardScanner.class, BPMSwitchYardScanner.class, TransformSwitchYardScanner.class }, mixins = {
		CDIMixIn.class, HornetQMixIn.class })
public class StoreRulesServiceTest {

	@ServiceOperation("StoreSelectionService.process")
	private Invoker doStore;

	@Test
	public void testStoreDocument() {

		FileDescriptorDTO fd = new FileDescriptorDTO("text.txt");
		fd.setExtension("jpeg");
		FileDTO f = new FileDTO(fd, "jeezus".getBytes(Charset.forName("UTF-8")));

		ExchangeHandler provider = new BaseHandler();
		Exchange ex = this.doStore.createExchange(provider);

		ex.getContext().setProperty("destinationRoutes", Sets.newHashSet());
		Message m = ex.createMessage().setContent(f);
		ex.send(m);

	}

}
