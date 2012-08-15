package cz.cvut.fit.culkajac.dp.services.store;

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

import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = "src/main/webResources" + SwitchYardTestCaseConfig.SWITCHYARD_XML, scanners = {
		BeanSwitchYardScanner.class, RulesSwitchYardScanner.class, BPMSwitchYardScanner.class, TransformSwitchYardScanner.class,
		RouteScanner.class }, mixins = { CDIMixIn.class, HornetQMixIn.class  })
public class StoreAggregatorServiceTest {

	@ServiceOperation("StoreAggregator.process")
	private Invoker doStore;

	@Test
	public void testStoreDocument() {

		ExchangeHandler provider = new BaseHandler();

		FileDescriptorDTO fd = new FileDescriptorDTO("text2.txt");
		Exchange ex = this.doStore.createExchange(provider);
		Message m = ex.createMessage().setContent(fd);

		FileDescriptorDTO fd2 = new FileDescriptorDTO("text3.txt");
		Exchange ex2 = this.doStore.createExchange(provider);
		Message m2 = ex.createMessage().setContent(fd2);

		FileDescriptorDTO fd3 = new FileDescriptorDTO("text4.txt");
		Exchange ex3 = this.doStore.createExchange(provider);
		Message m3 = ex.createMessage().setContent(fd3);

		FileDescriptorDTO fd4 = new FileDescriptorDTO("text5.txt");
		Exchange ex4 = this.doStore.createExchange(provider);
		Message m4 = ex.createMessage().setContent(fd4);

		int[] a = { 2, 2, 2, 2 };
		ex.getContext().setProperty("destinationRoutes", a, Scope.IN);
		ex2.getContext().setProperty("destinationRoutes", a, Scope.IN);
		ex3.getContext().setProperty("destinationRoutes", a, Scope.IN);
		ex4.getContext().setProperty("destinationRoutes", a, Scope.IN);

		ex.getContext().setProperty("processId", 126, Scope.IN);
		ex2.getContext().setProperty("processId", 122, Scope.IN);
		ex3.getContext().setProperty("processId", 126, Scope.IN);
		ex4.getContext().setProperty("processId", 126, Scope.IN);

		ex.send(m);
		ex2.send(m2);
		ex3.send(m3);
		ex4.send(m4);

	}
}
