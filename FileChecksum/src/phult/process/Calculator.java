package phult.process;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import phult.view.View;

public class Calculator implements ActionListener {
	private View view;

	public Calculator(View currentView) {
		this.view = currentView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String cmd = e.getActionCommand();
			if ("browse".equals(cmd)) {
				String filePath = view.getPathFile();
				view.setText(1, filePath);
			} else if ("calculate".equals(cmd)) {
				String path = view.getText();
				File file = new File(path);
				if (!isAFile(file)) {
					view.showMessage("File not found!!!");
					return;
				}
				String md5 = createChecksume(file, "MD5");
				String sha1 = createChecksume(file, "SHA1");
				view.setText(2, md5);
				view.setText(3, sha1);
			}
		} catch (NoSuchAlgorithmException e3) {
			// Cannot be here
		} catch (FileNotFoundException e1) {
			view.showMessage("File not found!!!");
		} catch (IOException e2) {
			view.showMessage("Program error!!!");
		} catch (Exception exception) {
			view.showMessage("Something Wrong!!!");

		}
	}

	public String createChecksume(File file, String CASAName)
			throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		MessageDigest digest = MessageDigest.getInstance(CASAName);
		InputStream fis = new FileInputStream(file);
		int n = 0;
		byte[] buffer = new byte[8192];
		while (n != -1) {
			n = fis.read(buffer);
			if (n > 0) {
				digest.update(buffer, 0, n);
			}
		}
		fis.close();

		return new HexBinaryAdapter().marshal(digest.digest()).toLowerCase();
	}

	private boolean isAFile(File file) {
		return file.exists();
	}

}
