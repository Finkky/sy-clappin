package cz.cvut.fit.culkajac.dp.services.retrieve;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import junit.framework.Assert;

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
import cz.cvut.fit.culkajac.dp.dto.OperationStatusDTO;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = "src/main/webResources" + SwitchYardTestCaseConfig.SWITCHYARD_XML, scanners = {
		BeanSwitchYardScanner.class, RulesSwitchYardScanner.class, BPMSwitchYardScanner.class, TransformSwitchYardScanner.class,
		RouteScanner.class }, mixins = { CDIMixIn.class, HornetQMixIn.class })
public class RetrieveServiceTest {

	@ServiceOperation("RetrieveFileService.process")
	private Invoker doStore;

	@Test
	public void testRetrieve() {

		FileDescriptorDTO fd = new FileDescriptorDTO("ano.jpg");

//		fd.setExtension("jpeg");

		Object o = this.doStore.sendInOut(fd).getContent();

		System.out.println(o);
	}

	@Test
	public void testRetrieveS3Soap() throws IOException {

		FileDescriptorDTO fd = new FileDescriptorDTO("text.jpg");

		fd.setExtension("jpeg");
		fd.setContentLength(500L);

		Collection<OperationStatusDTO<FileDTO>> o = this.doStore.sendInOut(fd).getContent(Collection.class);

		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream((new File("D:/archive3.zip"))));

		for (OperationStatusDTO<FileDTO> status : o) {

			System.out.println(status);

			if (status.getStatus() == 200) {

				ZipEntry entry = new ZipEntry(status.getServiceName() + "_" + status.getContent().getMetadata().getTitle());
				zos.putNextEntry(entry);
				IOUtils.copy(new ByteArrayInputStream(status.getContent().getData()), zos);
				zos.closeEntry();
				// break;
			}

		}
		zos.close();
	}

	@Test
	public void testRegex() {
		Pattern p = Pattern.compile("[a-zA-Z_0-9]{1,20}\\.[a-zA-Z0-9]{1,5}");
		String t1 = "test.txt";
		String t2 = "*-*--est.txt";
		String t3 = "est.tXt";

		Assert.assertTrue(p.matcher(t1).matches());
		Assert.assertFalse(p.matcher(t2).matches());
		Assert.assertTrue(p.matcher(t3).matches());
	}
}
