package TopPVP.com.MoNeYBaGS_.Configurations;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.MoNeYBaGS_.TopPVP;


public class Files {

	public Files(TopPVP plugin)
	{
		File players = new File("plugins/TopPVP/players.conf");
		File config = new File("plugins/TopPVP/config.yml");
		File template = new File("plugins/TopPVP/config_Template.yml");
		File playersconfig = new File("plugins/TopPVP/players.yml");

		try {
			if(!playersconfig.exists())
				players.createNewFile();
			if(!(players.exists()))
				players.createNewFile();
			if(!(config.exists()))
				config.createNewFile();
			if(!(template.exists()))
				template.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void copyFile(InputStream in, File out) throws Exception {
		if(!out.exists())
			out.createNewFile();
		InputStream fis = in;
		FileOutputStream fos = new FileOutputStream(out);
		try {
			byte[] buf = new byte[1024];
			int i = 0;
			while ((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
	}
}
