package cz.cvut.fit.culkajac.dp.services.retrieve;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.BaseHandler;
import org.switchyard.Exchange;
import org.switchyard.ExchangeHandler;
import org.switchyard.Message;
import org.switchyard.Scope;
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

import com.google.common.collect.Sets;

import cz.cvut.fit.culkajac.dp.Constants;
import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = "src/main/webResources" + SwitchYardTestCaseConfig.SWITCHYARD_XML, scanners = {
		BeanSwitchYardScanner.class, RulesSwitchYardScanner.class, BPMSwitchYardScanner.class, TransformSwitchYardScanner.class,
		RouteScanner.class }, mixins = { CDIMixIn.class, HornetQMixIn.class })
public class RetrieveRulesServiceTest {

	@ServiceOperation("RetrieveSelectionService.process")
	private Invoker doStore;

	@Test
	public void testStoreDocument() {

		FileDescriptorDTO fd = new FileDescriptorDTO("text.txt");
		fd.setExtension("jpeg");
		fd.setTargetService(Constants.GoogleDocs.ROUTE_ID);

		ExchangeHandler provider = new BaseHandler();
		Exchange ex = this.doStore.createExchange(provider);

		Set<String> hook = Sets.newHashSet();
		ex.getContext().setProperty("destinationRoutes", hook, Scope.IN);
		Message m = ex.createMessage().setContent(fd);
		ex.send(m);

		System.out.println(hook);

		// this.doStore.sendInOnly(r);

	}

}
