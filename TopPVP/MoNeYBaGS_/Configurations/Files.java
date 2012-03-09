package MoNeYBaGS_.Configurations;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import MoNeYBaGS_.TopPVP;

public class Files {
	public Files()
	{
		File players = new File("plugins/TopPVP/players.conf");
		File config = new File("plugins/TopPVP/config.yml");
		File playerconfig = new File("plugins/TopPVP/players.yml");
		File template = new File("plugins/TopPVP/config_Template.yml");
		
		try {
			if(!(players.exists()))
			{
				players.createNewFile();
			}
			if(!(config.exists()))
			{
				config.createNewFile();
			}
			if(!(playerconfig.exists()))
				config.createNewFile();
			if(!(template.exists()))
				template.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void extract(String... names) {
        for(String name: names) 
        {
            File actual = new File("/plugins/TopPVP/" + name);
            
            InputStream input = TopPVP.class.getResourceAsStream("resources/" + name);
            
            if(input != null)
            	continue;
            
            System.out.println("Extracting..." + name);
            
            FileOutputStream output = null;

            try {
                output = new FileOutputStream(actual);
                byte[] buf = new byte[8192];
                int length = 0;

                while ((length = input.read(buf)) > 0)
                    output.write(buf, 0, length);
            } catch (Exception e) {
            } finally {
                try { if (input != null) input.close();
                } catch (Exception e) { }

                try { if (output != null) output.close();
                } catch (Exception e) { }
            }
        }
    }
}
