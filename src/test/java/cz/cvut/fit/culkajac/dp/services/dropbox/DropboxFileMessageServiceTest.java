package cz.cvut.fit.culkajac.dp.services.dropbox;

import java.nio.charset.Charset;
import java.util.Collection;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.component.bean.config.model.BeanSwitchYardScanner;
import org.switchyard.component.bpm.config.model.BPMSwitchYardScanner;
import org.switchyard.component.camel.config.model.RouteScanner;
import org.switchyard.component.rules.config.model.RulesSwitchYardScanner;
import org.switchyard.test.InvocationFaultException;
import org.switchyard.test.Invoker;
import org.switchyard.test.ServiceOperation;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.test.mixins.CDIMixIn;
import org.switchyard.test.mixins.HornetQMixIn;
import org.switchyard.transform.config.model.TransformSwitchYardScanner;

import cz.cvut.fit.culkajac.dp.FileServiceException;
import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = "src/main/webResources" + SwitchYardTestCaseConfig.SWITCHYARD_XML, scanners = {
		BeanSwitchYardScanner.class, RulesSwitchYardScanner.class, BPMSwitchYardScanner.class, TransformSwitchYardScanner.class,
		RouteScanner.class }, mixins = { CDIMixIn.class, HornetQMixIn.class })
public class DropboxFileMessageServiceTest {

	private static final String serviceName = "DropboxServiceAdapter";

	@ServiceOperation(serviceName + ".storeOverwrite")
	private Invoker doStore;

	@ServiceOperation(serviceName + ".delete")
	private Invoker doDelete;

	@ServiceOperation(serviceName + ".retrieve")
	private Invoker doRetrieve;

	@ServiceOperation(serviceName + ".retrieveCurrentDelta")
	private Invoker doGetNewDelta;

	@Test
	public void testDeltaDocument() {
		@SuppressWarnings("unchecked")
		Collection<FileDescriptorDTO> fds = this.doGetNewDelta.sendInOut(null).getContent(Collection.class);
		/* Manual check */
		System.out.println(fds);

	}

	@Test
	public void testStoreDocument() {
		FileDescriptorDTO fd = new FileDescriptorDTO("text.txt");
		FileDTO f = new FileDTO(fd, "CLoud-APPs INtegration.".getBytes(Charset.forName("UTF-8")));
		Object o = this.doStore.sendInOut(f).getContent();
		/* Manual check */
		System.out.println(o);
	}

	@Test
	public void testDeleteStoreRetrieve() {
		final String tempName = String.format("%s.txt", RandomStringUtils.random(12, true, true));
		FileDescriptorDTO fd = new FileDescriptorDTO(tempName);
		try {
			this.doDelete.sendInOut(fd).getContent();
		} catch (InvocationFaultException ifEx) {
			Assert.assertTrue(ifEx.isType(FileServiceException.class));
		}

		try {
			this.doRetrieve.sendInOut(fd).getContent();
			Assert.fail("FileServiceException expected, no file should be returned.");
		} catch (InvocationFaultException ifEx) {
			Assert.assertTrue(ifEx.isType(FileServiceException.class));
		}

		FileDTO f = new FileDTO(fd, "I was here, FANTOMAS!".getBytes(Charset.forName("UTF-8")));
		try {
			this.doStore.sendInOut(f).getContent();
		} catch (InvocationFaultException ifEx) {
			Assert.fail("Unexpected Exception" + ifEx.getCause().getMessage());
		}

		try {
			FileDTO rf = this.doRetrieve.sendInOut(fd).getContent(FileDTO.class);
			Assert.assertEquals(f, rf);
		} catch (InvocationFaultException ifEx) {
			Assert.fail("Unexpected Exception" + ifEx.getCause().getMessage());
		}

		try {
			this.doDelete.sendInOut(fd).getContent();
		} catch (InvocationFaultException ifEx) {
			Assert.fail("Unexpected Exception" + ifEx.getCause().getMessage());
		}

	}
}
