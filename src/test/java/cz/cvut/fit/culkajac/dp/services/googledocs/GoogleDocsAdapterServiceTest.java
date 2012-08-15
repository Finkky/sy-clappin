package cz.cvut.fit.culkajac.dp.services.googledocs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import junit.framework.TestCase;

import org.apache.commons.io.IOUtils;
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
import org.switchyard.transform.config.model.TransformSwitchYardScanner;

import cz.cvut.fit.culkajac.dp.FileServiceException;
import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = "src/main/webResources" + SwitchYardTestCaseConfig.SWITCHYARD_XML, scanners = {
		BeanSwitchYardScanner.class, RulesSwitchYardScanner.class, BPMSwitchYardScanner.class, TransformSwitchYardScanner.class,
		RouteScanner.class }, mixins = { CDIMixIn.class }, exclude = { "camel", "soap", "rules", "bpm" })
public class GoogleDocsAdapterServiceTest extends TestCase {

	private static final String serviceName = "GoogleDocsServiceAdapter";

	@ServiceOperation(serviceName + ".delete")
	private Invoker doDelete;

	@ServiceOperation(serviceName + ".store")
	private Invoker doStore;

	@ServiceOperation(serviceName + ".retrieve")
	private Invoker doRetrieve;

	@Test
	public void testOnDeleteException() {
		try {
			FileDescriptorDTO fd = new FileDescriptorDTO("Cant find that");
			this.doDelete.sendInOut(fd).getContent();
			Assert.fail(String.format("Exception %s was expected. ", FileServiceException.class.getSimpleName()));
		} catch (InvocationFaultException ifEx) {
			Assert.assertTrue(ifEx.isType(FileServiceException.class));
		}
	}

	@Test
	public void testStoreDocument() {
		try {
			FileDescriptorDTO fd = new FileDescriptorDTO("text.txt");
			FileDTO f = new FileDTO(fd, "jeez".getBytes(Charset.forName("UTF-8")));
			this.doStore.sendInOut(f).getContent();
		} catch (InvocationFaultException ifEx) {
			Assert.fail(String.format("Exception %s was not expected. ", ifEx.getCause().getClass()));
		}
	}

	@Test
	public void testStoreBigPDFDocument() throws IOException {
		try {
			FileInputStream fis = new FileInputStream(new File("/testfiles/fit_big.pdf"));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			IOUtils.copy(fis, baos);

			FileDescriptorDTO fd = new FileDescriptorDTO("fit_big.pdf");
			FileDTO f = new FileDTO(fd, baos.toByteArray());
			this.doStore.sendInOut(f).getContent();
		} catch (InvocationFaultException ifEx) {
			Assert.fail(String.format("Exception %s was not expected. ", ifEx.getCause().getClass()));
		}
	}

	@Test
	public void testStoreAndDeleteDocument() {
		try {
			FileDescriptorDTO fd = new FileDescriptorDTO("text.txt");
			FileDTO f = new FileDTO(fd, "FIT".getBytes(Charset.forName("UTF-8")));
			FileDescriptorDTO storedFd = this.doStore.sendInOut(f).getContent(FileDescriptorDTO.class);
			System.out.println(fd.toString());
			System.out.println(storedFd.toString());

			FileDescriptorDTO nfd = new FileDescriptorDTO("text.txt");
			System.out.println(nfd.toString());
			this.doDelete.sendInOnly(nfd);
		} catch (InvocationFaultException ifEx) {
			Assert.fail(String.format("Exception %s was not expected. ", ifEx.getCause().getClass()));
		}
	}

	@Test
	public void testRetrieveDocument() {
		try {
			FileDescriptorDTO fd = new FileDescriptorDTO("file.txt");
			FileDTO f = this.doRetrieve.sendInOut(fd).getContent(FileDTO.class);
			System.out.println(new String(f.getData(), Charset.forName("UTF-8")));
		} catch (InvocationFaultException ifEx) {
			Assert.fail(String.format("Exception %s was not expected. ", ifEx.getCause().getClass()));
		}
	}

	@Test
	public void testRetrieveDocumentAndStoreLocaly() throws IOException {
		try {
			FileDescriptorDTO fd = new FileDescriptorDTO("fit_big.pdf");
			FileDTO f = this.doRetrieve.sendInOut(fd).getContent(FileDTO.class);
			FileOutputStream fos = new FileOutputStream("/testfiles/out/fit_big.pdf");
			fos.write(f.getData());
			fos.flush();
			fos.close();
		} catch (InvocationFaultException ifEx) {
			Assert.fail(String.format("Exception %s was not expected. ", ifEx.getCause().getClass()));
		}
	}
}