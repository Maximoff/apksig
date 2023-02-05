package ru.maximoff.apksig;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.apksig.ApkVerifier;
import java.io.File;
import java.security.cert.X509Certificate;
import java.util.List;

public class MainActivity extends Activity {
	private TextView textView;
	private EditText editText;
	private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		textView = findViewById(R.id.mainTextView1);
		editText = findViewById(R.id.mainEditText1);
		button = findViewById(R.id.mainButton1);
		button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View p1) {
					try {
						verify(editText.getText().toString());
					} catch (Exception e) {
						textView.setText("Error: " + e.toString());
					}
				}
			});
    }

	private void verify(String path) throws Exception {
        File inputApk = new File(path);
        int minSdkVersion = 1;
        int maxSdkVersion = Integer.MAX_VALUE;
        File v4SignatureFile = new File(path + ".idsig");
        ApkVerifier.Builder apkVerifierBuilder = new ApkVerifier.Builder(inputApk);
		apkVerifierBuilder.setMinCheckedPlatformVersion(minSdkVersion);
		apkVerifierBuilder.setMaxCheckedPlatformVersion(maxSdkVersion);
        if (v4SignatureFile.isFile()) {
            apkVerifierBuilder.setV4SignatureFile(v4SignatureFile);
        }
        ApkVerifier apkVerifier = apkVerifierBuilder.build();
        ApkVerifier.Result result = apkVerifier.verify();
        boolean verified = result.isVerified();
		StringBuilder sb = new StringBuilder();
        if (verified) {
            List<X509Certificate> signerCerts = result.getSignerCertificates();
			sb.append("Verifies");
			sb.append("\n");
			sb.append(
				"Verified using v1 scheme (JAR signing): "
				+ result.isVerifiedUsingV1Scheme());
			sb.append("\n");
			sb.append(
				"Verified using v2 scheme (APK Signature Scheme v2): "
				+ result.isVerifiedUsingV2Scheme());
			sb.append("\n");
			sb.append(
				"Verified using v3 scheme (APK Signature Scheme v3): "
				+ result.isVerifiedUsingV3Scheme());
			sb.append("\n");
			sb.append(
				"Verified using v3.1 scheme (APK Signature Scheme v3.1): "
				+ result.isVerifiedUsingV31Scheme());
			sb.append("\n");
			sb.append(
				"Verified using v4 scheme (APK Signature Scheme v4): "
				+ result.isVerifiedUsingV4Scheme());
			sb.append("\n");
			sb.append("Verified for SourceStamp: " + result.isSourceStampVerified());
			sb.append("\n");
			sb.append("Number of signers: " + signerCerts.size());
        } else {
            sb.append("DOES NOT VERIFY");
        }
		textView.setText(sb.toString());
    }
}
