package cz.cvut.fit.culkajac.dp.services.amazons3.impl;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import net.iharder.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.switchyard.component.bean.Service;

import cz.cvut.fit.culkajac.dp.Constants;
import cz.cvut.fit.culkajac.dp.services.amazons3.AmazonS3Signable;
import cz.cvut.fit.culkajac.dp.services.amazons3.SecurityAdapter;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.DeleteObject;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.GetObject;
import cz.cvut.fit.culkajac.dp.services.amazons3.dto.PutObjectInline;

@Service(SecurityAdapter.class)
public class SecurityAdapterImpl implements SecurityAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityAdapterImpl.class);
	//
	private static final String ACCESS_KEY = Constants.AmazonS3.ACCESS_KEY;
	private static final String SECRET_ACCESS_KEY = Constants.AmazonS3.SECRET_ACCESS_KEY;
	//
	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	private static final Charset UTF8 = Charset.forName("UTF-8");
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	private static final SecretKeySpec SECRET_KEY = new SecretKeySpec(SECRET_ACCESS_KEY.getBytes(), HMAC_SHA1_ALGORITHM);

	private Mac sha256Hmac;

	public SecurityAdapterImpl() {

		DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("Zulu"));

		try {
			this.sha256Hmac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
			this.sha256Hmac.init(SECRET_KEY);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public GetObject signGet(GetObject r) {
		return sign(r);
	}

	@Override
	public PutObjectInline signPut(PutObjectInline r) {
		return sign(r);
	}

	@Override
	public DeleteObject signDelete(DeleteObject r) {
		return sign(r);
	}

	@Override
	public <T extends AmazonS3Signable> T sign(T r) {

		Date timestamp = Calendar.getInstance().getTime();
		String stringToSign = "AmazonS3" + r.getClass().getSimpleName() + DATE_FORMAT.format(timestamp);
		String signature = calculateRFC2104HMAC(stringToSign);

		r.setAWSAccessKeyId(ACCESS_KEY);
		r.setTimestamp(timestamp);
		r.setSignature(signature);
		return r;
	}

	private String calculateRFC2104HMAC(String s) {
		byte[] signatureBytes = this.sha256Hmac.doFinal(s.getBytes(UTF8));
		return Base64.encodeBytes(signatureBytes);
	}
}
