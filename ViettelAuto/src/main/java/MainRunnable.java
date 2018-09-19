import com.vt.ViettelAutoProcessor;
import com.vt.dto.LoginDTO;
import com.vt.dto.LoginMethodEnum;
import com.vt.dto.NapTheDTO;
import com.vt.dto.NapTheMethodEnum;

public class MainRunnable extends Thread {
	private final static String PATH_TO_EXE_SELENIUM = "C:\\Users\\Admin\\git\\mainRabbitMQClient\\testRabbitMQ\\tool\\phantomjs\\phantomjs.exe";

	public static void main(String[] args) {
		Thread threadKeepSession = new Thread() {
			public void run() {
				LoginDTO loginDto = new LoginDTO("h004_gftth_longnd193", "020791a@", LoginMethodEnum.ADSL_FTTH_NEXTTV);
				NapTheDTO naptheDto = new NapTheDTO(NapTheMethodEnum.DIDONG_DCOM, "0961005566", "511380099753328");

				for (int i = 0; i < 2; i++) {
					System.out.println("Chay lan : " + i);
					try {
						Thread.sleep(1000);
						ViettelAutoProcessor viettelAuto = new ViettelAutoProcessor(loginDto, naptheDto,PATH_TO_EXE_SELENIUM);
						viettelAuto.execute();
					} catch (InterruptedException e) { 
						e.printStackTrace();
					}

				}
			}
		};
		threadKeepSession.start();
	}

}
