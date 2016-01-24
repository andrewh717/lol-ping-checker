import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PingChecker{
	static String [] pingStrings = new String[4];
	public static void runSystemCommand(String command){
		try{
			Process p = Runtime.getRuntime().exec(command);
			BufferedReader inputStream = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
			String s = "";
			int counter = 0;
			
			// reading output stream of the command
			while((s = inputStream.readLine()) != null && counter < 4){
				System.out.println(s);
				String pingString = s.substring(s.length()-7);
				//pingString = pingString.replaceAll("[^\\d.]", "");
				if(pingString.equals("f data.") == false){
					pingStrings[counter] = pingString;
					counter++;
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		
		String ip = "104.160.131.1";
		runSystemCommand("ping " + ip);
		double[] pingDoubles = new double[4];
		double sum = 0;
		for(int j = 0; j < 4; j++){
			pingStrings[j] = pingStrings[j].replaceAll("[^\\d.]", "");
			pingDoubles[j] = Double.parseDouble(pingStrings[j]);
			sum += pingDoubles[j];	
		}
		int avg = Math.round((float)(sum/4.0));
		System.out.println("Average Ping: " + avg + "ms");
	}
}