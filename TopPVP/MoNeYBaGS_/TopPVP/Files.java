package MoNeYBaGS_.TopPVP;

import java.io.File;
import java.io.IOException;

public class Files {
	public Files()
	{
		File players = new File("plugins/TopPVP/players.conf");
		File config = new File("plugins/TopPVP/config.yml");
		
		try {
			if(!(players.exists()))
			{
				players.createNewFile();
			}
			if(!(config.exists()))
			{
				config.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
