package cz.cvut.fit.culkajac.dp.services.googlephotos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

import junit.framework.TestCase;

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
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.Status;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = "src/main/webResources" + SwitchYardTestCaseConfig.SWITCHYARD_XML, scanners = {
		BeanSwitchYardScanner.class, RulesSwitchYardScanner.class, BPMSwitchYardScanner.class, TransformSwitchYardScanner.class,
		RouteScanner.class }, mixins = { CDIMixIn.class }, exclude = { "camel", "soap", "rules", "bpm" })
public class GooglePhotosServiceAdapterTest extends TestCase {

	private static final String serviceName = "GooglePhotosServiceAdapter";
	private static final String dropboxServiceName = "DropboxServiceAdapter";

	@ServiceOperation(serviceName + ".delete")
	private Invoker doDelete;

	@ServiceOperation(serviceName + ".store")
	private Invoker doStore;

	@ServiceOperation(serviceName + ".retrieve")
	private Invoker doRetrieve;

	@ServiceOperation(serviceName + ".retrieveLatest")
	private Invoker doRetrieveLatest;

	@ServiceOperation(dropboxServiceName + ".retrieve")
	private Invoker doRetrieveDropbox;

	// @Test
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
	public void testDelete() {

		FileDescriptorDTO fd = new FileDescriptorDTO("fighter.jpg");
		this.doDelete.sendInOut(fd).getContent();
	}

	@Test
	public void testStoreDocument() {
		try {

			FileDescriptorDTO fd = new FileDescriptorDTO("fighter.jpg");
			FileDTO file = this.doRetrieveDropbox.sendInOut(fd).getContent(FileDTO.class);
			FileDescriptorDTO s = this.doStore.sendInOut(file).getContent(FileDescriptorDTO.class);

			System.out.println(s);

		} catch (InvocationFaultException ifEx) {
			Assert.fail(String.format("Exception %s was not expected. ", ifEx.getCause().getClass()));
		}
	}

	@Test
	public void testRetrieveDocument() throws IOException {
		try {
			FileDescriptorDTO fd = new FileDescriptorDTO("fighter.jpg");
			FileDTO f = this.doRetrieve.sendInOut(fd).getContent(FileDTO.class);

			FileOutputStream fos = new FileOutputStream(new File("d:/img.jpg"));

			fos.write(f.getData());
			fos.flush();
			fos.close();
		} catch (InvocationFaultException ifEx) {
			Assert.fail(String.format("Exception %s was not expected. ", ifEx.getCause().getClass()));
		}
	}

	@Test
	public void testRetrieveLatestDocument() throws IOException {
		try {
			FileDescriptorDTO fd = new FileDescriptorDTO("fighter.jpg");
			FileDTO f = this.doRetrieveLatest.sendInOut(fd).getContent(FileDTO.class);

			FileOutputStream fos = new FileOutputStream(new File("d:/imgx2.jpg"));

			fos.write(f.getData());
			fos.flush();
			fos.close();
		} catch (InvocationFaultException ifEx) {
			Assert.fail(String.format("Exception %s was not expected. ", ifEx.getCause().getClass()));
		}
	}

	// @Test
	public void testRetrieveDocumentAndStoreLocaly() throws IOException {
		try {
			FileDescriptorDTO fd = new FileDescriptorDTO("x.txt");
			FileDTO f = this.doRetrieve.sendInOut(fd).getContent(FileDTO.class);
			System.out.println(new String(f.getData(), Charset.forName("UTF-8")));

			FileOutputStream fos = new FileOutputStream("d:/x.txt");
			fos.write(f.getData());
			fos.flush();
			fos.close();
		} catch (InvocationFaultException ifEx) {
			Assert.fail(String.format("Exception %s was not expected. ", ifEx.getCause().getClass()));
		}
	}
}